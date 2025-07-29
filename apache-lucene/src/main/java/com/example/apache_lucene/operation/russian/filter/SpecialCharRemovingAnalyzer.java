package com.example.apache_lucene.operation.russian.filter;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.pattern.PatternReplaceFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import java.util.regex.Pattern;

public class SpecialCharRemovingAnalyzer extends Analyzer {
    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        Tokenizer tokenizer = new StandardTokenizer();

        // Remove all non-letter, non-digit characters
        TokenStream tokenStream = new PatternReplaceFilter(
                tokenizer,
                Pattern.compile("[^\\p{L}\\p{Nd}]+"), // match non-letter/digit characters
                "",
                true
        );

        return new TokenStreamComponents(tokenizer, tokenStream);
    }
}
