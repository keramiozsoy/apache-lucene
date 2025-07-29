package com.example.apache_lucene.api;

import com.example.apache_lucene.service.LuceneService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/example")
public class LuceneController {
    private final LuceneService luceneService;

    public LuceneController(LuceneService luceneService) {
        this.luceneService = luceneService;
    }

    @GetMapping("/0/defaultNormalize")
    public String defaultNormalize(
            @RequestParam(defaultValue = "The -LAZY cat'.") String text) {
        return luceneService.defaultNormalize(text);
    }


    @GetMapping("/1/lowercase")
    public String lowercase(
            @RequestParam(defaultValue = "The -LAZY cat'.") String text) {
        return luceneService.lowercase(text);
    }

    @GetMapping("/2/deletePunctuation")
    public String deletePunctuation(
            @RequestParam(defaultValue = "The -LAZY cat'.") String text) {
        return luceneService.deletePunctuation(text);
    }

    @GetMapping("/3/lowercaseAndDeletePunctuation")
    public String lowercaseAndDeletePunctuation(
            @RequestParam(defaultValue = "The -LAZY cat'.") String text) {
        String lowercase = luceneService.lowercase(text);
        return luceneService.deletePunctuation(lowercase);
    }

    @GetMapping("/4/russian/doLanguageNormalize")
    public String russianDoLanguageNormalize(
            @RequestParam(defaultValue = "-Привет'.") String text) {
        return luceneService.russianDoLanguageNormalize(text);
    }


}
