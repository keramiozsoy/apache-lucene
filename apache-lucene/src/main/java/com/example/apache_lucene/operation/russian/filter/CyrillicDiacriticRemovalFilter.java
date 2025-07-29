package com.example.apache_lucene.operation.russian.filter;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;

public final class CyrillicDiacriticRemovalFilter extends TokenFilter {

    private final CharTermAttribute termAttr = addAttribute(CharTermAttribute.class);

    protected CyrillicDiacriticRemovalFilter(TokenStream input) {
        super(input);
    }

    @Override
    public boolean incrementToken() throws IOException {
        if (!input.incrementToken()) return false;

        char[] buffer = termAttr.buffer();
        int length = termAttr.length();

        for (int i = 0; i < length; i++) {
            if (buffer[i] == 'й' || buffer[i] == 'Й') {
                buffer[i] = (char) (buffer[i] == 'й' ? 'и' : 'И');
            }
        }

        return true;
    }
}
