package com.example.apache_lucene.service;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.core.KeywordTokenizer;
import org.apache.lucene.analysis.pattern.PatternReplaceCharFilter;
import org.apache.lucene.analysis.ru.RussianAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.BytesRef;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.util.regex.Pattern;

@Service
public class LuceneService {
    public String defaultNormalize(String text) {
       Analyzer analyzer = new StandardAnalyzer();
        return convertWithNormalize(analyzer, text);
    }


    public String lowercase(String text) {
        Analyzer analyzer = new Analyzer() {
            @Override
            protected TokenStreamComponents createComponents(String fieldName) {
                Tokenizer tokenizer = new KeywordTokenizer();
                TokenStream tokenStream = new LowerCaseFilter(tokenizer);
                return new TokenStreamComponents(tokenizer, tokenStream);
            }
        };

        return convertWithTokenStream(analyzer, text);
    }

    public String deletePunctuation(String text) {
        Analyzer analyzer = new Analyzer() {
            @Override
            protected Reader initReader(String fieldName, Reader reader) {
                return new PatternReplaceCharFilter(Pattern.compile("\\p{Punct}"), "", reader);
            }

            @Override
            protected TokenStreamComponents createComponents(String fieldName) {
                Tokenizer tokenizer = new KeywordTokenizer();
                return new TokenStreamComponents(tokenizer);
            }
        };

        return convertWithTokenStream(analyzer, text);
    }

    private String convertWithNormalize(Analyzer analyzer, String text){
        BytesRef normalize = analyzer.normalize("field", text);
        return normalize.utf8ToString();
    }

    private String convertWithTokenStream(Analyzer analyzer, String text) {
        StringBuilder builder = new StringBuilder();
        TokenStream tokenStream = analyzer.tokenStream("field", text);
        CharTermAttribute attr = tokenStream.addAttribute(CharTermAttribute.class);
        try {
            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                builder.append(attr.toString());
            }
            tokenStream.end();
            analyzer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return builder.toString();
    }


    public String russianDoLanguageNormalize(String text) {
        Analyzer analyzer = new RussianAnalyzer();
        return convertWithTokenStream(analyzer, text);
    }
}
