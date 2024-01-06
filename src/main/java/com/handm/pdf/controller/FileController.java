package com.handm.pdf.controller;

import com.handm.pdf.util.FileUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author lqm
 * @date 2024/1/6 17:24
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping(value = "/pdf/toWord", consumes = "multipart/form-data")
    public void toWord(HttpServletResponse response, @RequestParam MultipartFile file) throws IOException {
        FileUtil.pdfToWord(response, file.getBytes());
    }

    @PostMapping(value = "/word/toPdf", consumes = "multipart/form-data")
    public void toPdf(HttpServletResponse response, @RequestParam MultipartFile file) throws IOException {
        FileUtil.wordToPdf(response, file.getBytes());
    }
}
