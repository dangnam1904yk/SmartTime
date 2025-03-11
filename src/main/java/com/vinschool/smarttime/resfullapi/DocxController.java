// package com.vinschool.smarttime.resfullapi;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.core.io.InputStreamResource;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.multipart.MultipartFile;
// import com.darkprograms.speech.translator.GoogleTranslate;
// import com.vinschool.smarttime.service.DocxProcessorService;

// import java.io.ByteArrayInputStream;

// @RestController
// @RequestMapping("/api/docx")
// public class DocxController {

// @Autowired
// private DocxProcessorService docxProcessorService;

// @PostMapping("/process")
// public ResponseEntity<InputStreamResource> processDocx(@RequestParam("file")
// MultipartFile file) {
// if (file.isEmpty() || !file.getOriginalFilename().endsWith(".docx")) {
// return ResponseEntity.badRequest().body(null);
// }

// ByteArrayInputStream copiedDoc =
// docxProcessorService.copyDocxWithImages(file);

// return ResponseEntity.ok()
// .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;
// filename=processed_with_images.docx")
// .contentType(MediaType.APPLICATION_OCTET_STREAM)
// .body(new InputStreamResource(copiedDoc));
// }

// @PostMapping("/process1")
// public ResponseEntity<InputStreamResource> processDocx1(@RequestParam("file")
// MultipartFile file) {
// if (file.isEmpty() || !file.getOriginalFilename().endsWith(".docx")) {
// return ResponseEntity.badRequest().body(null);
// }

// // 1. Đọc và lấy văn bản từ file DOCX
// String extractedText = docxProcessorService.extractText(file);

// // 2. Xử lý văn bản ở đây (nếu cần)
// // Ví dụ: bạn có thể thêm thay đổi vào extractedText nếu cần xử lý dữ liệu

// // 3. Ghi lại văn bản vào file mới (giữ nguyên hình ảnh, bảng biểu)
// ByteArrayInputStream newDoc =
// docxProcessorService.writeDocxWithText(extractedText, file);

// return ResponseEntity.ok()
// .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;
// filename=processed_with_text.docx")
// .contentType(MediaType.APPLICATION_OCTET_STREAM)
// .body(new InputStreamResource(newDoc));
// }
// }