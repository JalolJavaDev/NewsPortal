package com.dailynews.Newsdaily.controller;

import com.dailynews.Newsdaily.domen.News;
import com.dailynews.Newsdaily.domen.NewsImage;
import com.dailynews.Newsdaily.repository.NewsImageRepository;
import com.dailynews.Newsdaily.service.NewsImageServices;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import java.time.Instant;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/api")
public class NewsImageController {
    private final NewsImageServices newsImageServices;
   // private final Logger logger = (Logger) LoggerFactory.getLogger(NewsImageController.class);
    private static final String UPLOADED_FOLDER = "E:\\spring boot projects\\Newsdaily\\src\\main\\java\\com\\dailynews\\Newsdaily\\image";
    public NewsImageController(NewsImageServices newsImageServices, NewsImageRepository newsImageRepository) {
        this.newsImageServices = newsImageServices;
        this.newsImageRepository = newsImageRepository;
    }

    @GetMapping("/newsImage")
    public List<NewsImage> getAll(){
        return newsImageServices.findAllImage()
                .stream()
                .map(this::mapToFileResponse)
                .collect(Collectors.toList());


    }
    private NewsImage mapToFileResponse(NewsImage fileEntity) {
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/images/")
                .path(String.valueOf((fileEntity.getImageId())))
                .toUriString();
        NewsImage fileResponse = new NewsImage();
        fileResponse.setImageId(fileEntity.getImageId());
        fileResponse.setImageSource(fileEntity.getImageSource());
        fileResponse.setContentType(fileEntity.getContentType());
        fileResponse.setContentSize(fileEntity.getContentSize());
        fileResponse.setNews(fileEntity.getNews());
       fileResponse.setData(fileEntity.getData());
        fileResponse.setUrl(downloadURL);
        return fileResponse;
    }
    @GetMapping("/images/{imageId}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long imageId) {
        Optional<NewsImage> fileEntityOptional = newsImageServices.getFile(imageId);

        if (fileEntityOptional.isEmpty()) {
            return ResponseEntity.notFound()
                    .build();
        }
        NewsImage fileEntity = fileEntityOptional.get();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getImageSource() + "\"")
                .contentType(MediaType.valueOf(fileEntity.getContentType()))
                .body(fileEntity.getData());
    }
    @GetMapping("/newsImage/news/{newsId}")
    public ResponseEntity< List<NewsImage>> getByNewsId( @PathVariable Long newsId){
        List<NewsImage> newsImage=  newsImageServices.findByNews(newsId);
        return ResponseEntity.ok(newsImage);
    }
    @GetMapping("/newsImage/{imageId}")
    public ResponseEntity<NewsImage> getOne(@PathVariable Long imageId){
        NewsImage newsImage=newsImageServices.findOne(imageId);
        return ResponseEntity.ok(newsImage);
    }

    @PostMapping("/newsImage")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadfile,
                                        @RequestParam("newsId") Long newsId,


                                        final HttpServletRequest request) {
        /** Below data is what we saving into database */


        if (uploadfile.isEmpty()) {
            return new ResponseEntity<String>("Iltimod biror faylni tanlang", HttpStatus.OK);
        }

        try {
            /** File will get saved to file system and database */
            saveUploadedFiles(List.of(uploadfile),newsId);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Muaffaqiyatli yuklandi !!! - " + uploadfile.getOriginalFilename(),
                new HttpHeaders(), HttpStatus.OK);

    }
    private void saveUploadedFiles(List<MultipartFile> files,Long newsId) throws IOException {
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }
            saveMetaData(file,newsId);

        }

    }
    private void saveMetaData(MultipartFile file,
                              Long newsId
                              ) throws IOException {
        NewsImage metaData = new NewsImage();
        metaData.setNews(new News(newsId));

        metaData.setImageCreationDTM(Instant.now());
        metaData.setActive(true);
        metaData.setData(file.getBytes());
      metaData.setUrl("api/images/"+file.getOriginalFilename());
        metaData.setImageSource(file.getOriginalFilename());
        metaData.setContentType(file.getContentType());
        metaData.setContentSize(file.getSize());
        newsImageRepository.save(metaData);
    }

    @DeleteMapping("/newsImage/{id}")
    public void delete(@PathVariable Long id){
        newsImageServices.delete(id);
    }
    private final NewsImageRepository newsImageRepository;


}