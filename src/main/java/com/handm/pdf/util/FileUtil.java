package com.handm.pdf.util;

import com.aspose.pdf.Document;
import com.aspose.pdf.SaveFormat;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author lqm
 * @date 2024/1/6 17:25
 */
@Slf4j
public class FileUtil {

    private FileUtil() {
        // do nothing
    }

    public static void pdfToWord(HttpServletResponse response, byte[] bytes) {
        String filename = "transform.docx";
        Document doc = new Document(new ByteArrayInputStream(bytes));
        try {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, StandardCharsets.UTF_8));
            doc.save(response.getOutputStream(), SaveFormat.DocX);
        } catch (Exception e) {
            log.error("Pdf 转 Word 失败...", e);
        } finally {
            doc.close();
        }
    }

    public static void wordToPdf(HttpServletResponse response, byte[] bytes) {
        String filename = "transform.pdf";
        try {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, StandardCharsets.UTF_8));
            com.aspose.words.Document doc = new com.aspose.words.Document(new ByteArrayInputStream(bytes));
            doc.save(response.getOutputStream(), com.aspose.words.SaveFormat.PDF);
            doc.cleanup();
        } catch (Exception e) {
            log.error("Word 转 Pdf 失败...", e);
        }
    }
}
