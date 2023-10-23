package com.web.mlog_server.common;

import com.web.mlog_server.mvc.post.model.PostFile;
import com.web.mlog_server.mvc.project.model.ProjectFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class FileUtil {
    @Value("${path.original}")
    private String originalFileRootPath;
    @Value("${path.thumbnail}")
    private String thumbnailFileRootPath;
    public PostFile getPostFile(MultipartFile multipartFile) {
        // DB에 저장하기 위해 파일의 정보를 얻음
        try {
            String uuid = UUID.randomUUID().toString();
            String originalFilename = multipartFile.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
            String fileName = uuid + "." + fileExtension;

            return PostFile.builder()
                    .uuid(uuid)
                    .mimeType(multipartFile.getContentType())
                    .originalFileName(originalFilename)
                    .fileName(fileName)
                    .fileSize(multipartFile.getSize())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
        }
    }
    public ProjectFile getProjectFile(MultipartFile multipartFile) {
        // DB에 저장하기 위해 파일의 정보를 얻음
        try {
            String uuid = UUID.randomUUID().toString();
            String originalFilename = multipartFile.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
            String fileName = uuid + "." + fileExtension;

            return ProjectFile.builder()
                    .uuid(uuid)
                    .mimeType(multipartFile.getContentType())
                    .originalFileName(originalFilename)
                    .fileName(fileName)
                    .fileSize(multipartFile.getSize())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
        }
    }

    public boolean uploadFile(MultipartFile multipartFile, String fileName) {
        // 파일을 서버에 저장함
        try {
            if (!new File(originalFileRootPath).exists()) {
                try {
                    new File(originalFileRootPath).mkdirs();
                } catch(Exception e) {
                    e.printStackTrace();
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "디렉토리 생성에 실패했습니다.");
                }
            }
            multipartFile.transferTo(Paths.get(originalFileRootPath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 저장을 실패했습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "예기치 못한 오류로 파일 저장을 실패했습니다.");
        }
        return true;
    }
}