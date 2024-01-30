package com.handm.pdf.util;

import com.aspose.pdf.Document;
import com.aspose.pdf.SaveFormat;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author lqm
 * @date 2024/1/6 17:25
 */
@Slf4j
public class FileUtil {

    private static final String FILENAME = "%s.%s";

    private FileUtil() {
        // do nothing
    }

    public static void transformPdf(HttpServletResponse response, MultipartFile file, String type) throws IOException {
        PDF pdf = PDF.valueOf(type.toUpperCase());
        String filename = getFilename(file.getOriginalFilename(), pdf.getValue());
        Document doc = new Document(new ByteArrayInputStream(file.getBytes()));
        try {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, String.valueOf(StandardCharsets.UTF_8)));
            doc.save(filename, pdf.getSaveFormat());
        } catch (Exception e) {
            log.error("Pdf 转 Word 失败...", e);
        } finally {
            doc.close();
        }
    }

    public static void transformWord(HttpServletResponse response, MultipartFile file, String type) {
        WORD word = WORD.valueOf(type);
        String filename = getFilename(file.getOriginalFilename(), word.getValue());
        try {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, String.valueOf(StandardCharsets.UTF_8)));
            com.aspose.words.Document doc = new com.aspose.words.Document(new ByteArrayInputStream(file.getBytes()));
            doc.save(response.getOutputStream(), word.getSaveFormat());
            doc.cleanup();
        } catch (Exception e) {
            log.error("Word 转 Pdf 失败...", e);
        }
    }

    private static String getFilename(String name, String suffix) {
        return String.format(FILENAME, name, suffix);
    }

    @Getter
    enum PDF {
        WORD("docx", SaveFormat.DocX),
        EXCEL("xlsx", SaveFormat.Excel),
        PPT("pptx", SaveFormat.Pptx),
        IMG("", SaveFormat.Pdf);

        PDF(String value, int saveFormat) {
            this.value = value;
            this.saveFormat = saveFormat;
        }

        private final String value;

        private final int saveFormat;
    }

    @Getter
    enum WORD {
        PDF("pdf", com.aspose.words.SaveFormat.PDF),
        PNG("png", com.aspose.words.SaveFormat.PNG);

        WORD(String value, int saveFormat) {
            this.value = value;
            this.saveFormat = saveFormat;
        }

        private final String value;

        private final int saveFormat;
    }
}
