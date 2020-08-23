package top.xlxs.eurekaconsumerfeign.service;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HiFeignServiceTester {

    @Autowired
    private FileFeignService fileFeignService;

    @Test
    void handleFileUpload() {
        File file = new File("C:\\Users\\Jin\\IdeaProjects\\eureka-consumer-feign\\mvnw.cmd");
        DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem(
                "file", MediaType.TEXT_PLAIN_VALUE, true, file.getName()
        );

        try {
            InputStream inputStream = new FileInputStream(file);
            OutputStream os = fileItem.getOutputStream();
            IOUtils.copy(inputStream, os);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid file: " + e, e);
        }

        MultipartFile multipartFile = new CommonsMultipartFile(fileItem);

        assertNotNull(fileFeignService.handleFileUpload(multipartFile));
    }
}