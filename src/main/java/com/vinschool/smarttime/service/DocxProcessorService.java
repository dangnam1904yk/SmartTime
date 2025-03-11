// package com.vinschool.smarttime.service;

// import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
// import org.apache.poi.xwpf.usermodel.XWPFDocument;
// import org.apache.poi.xwpf.usermodel.XWPFParagraph;
// import org.apache.poi.xwpf.usermodel.XWPFPictureData;
// import org.apache.poi.xwpf.usermodel.XWPFTable;
// import org.apache.poi.xwpf.usermodel.XWPFTableCell;
// import org.apache.poi.xwpf.usermodel.XWPFTableRow;
// import org.springframework.stereotype.Service;
// import org.springframework.web.multipart.MultipartFile;

// import java.io.ByteArrayInputStream;
// import java.io.ByteArrayOutputStream;
// import java.io.IOException;
// import java.util.List;
// import com.darkprograms.speech.translator.GoogleTranslate;

// @Service
// public class DocxProcessorService {

// // Đọc nội dung từ file DOCX
// public String readDocx(MultipartFile file) {
// StringBuilder content = new StringBuilder();

// try (XWPFDocument document = new XWPFDocument(file.getInputStream())) {
// // Đọc các đoạn văn bản
// for (XWPFParagraph para : document.getParagraphs()) {
// content.append(para.getText()).append("\n");
// }

// // Đọc nội dung trong bảng (nếu có)
// for (XWPFTable table : document.getTables()) {
// for (XWPFTableRow row : table.getRows()) {
// for (XWPFTableCell cell : row.getTableCells()) {
// content.append(cell.getText()).append("\t");
// }
// content.append("\n");
// }
// }
// } catch (IOException e) {
// e.printStackTrace();
// }

// return content.toString();
// }

// // Ghi nội dung vào file DOCX mới
// public ByteArrayInputStream writeDocx(String content) {
// try (XWPFDocument document = new XWPFDocument();
// ByteArrayOutputStream out = new ByteArrayOutputStream()) {

// XWPFParagraph paragraph = document.createParagraph();
// paragraph.createRun().setText(content);

// document.write(out);
// return new ByteArrayInputStream(out.toByteArray());
// } catch (IOException e) {
// e.printStackTrace();
// return null;
// }
// }

// public ByteArrayInputStream copyDocxWithImages(MultipartFile file) {
// try (XWPFDocument sourceDoc = new XWPFDocument(file.getInputStream());
// XWPFDocument targetDoc = new XWPFDocument();
// ByteArrayOutputStream out = new ByteArrayOutputStream()) {

// StringBuilder stringBuilder = new StringBuilder();
// // Copy các đoạn văn
// for (XWPFParagraph para : sourceDoc.getParagraphs()) {
// XWPFParagraph newPara = targetDoc.createParagraph();

// String ab = GoogleTranslate.translate("en", "vi", para.getText());
// stringBuilder.append(GoogleTranslate.translate("vi", para.getText()) + "\n");
// }
// XWPFParagraph newPara = targetDoc.createParagraph();
// newPara.createRun().setText(stringBuilder.toString());

// // Copy tất cả hình ảnh
// List<XWPFPictureData> pictures = sourceDoc.getAllPictures();
// for (XWPFPictureData pic : pictures) {
// try {
// targetDoc.addPictureData(pic.getData(), pic.getPictureType());
// } catch (InvalidFormatException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// }

// // Ghi file mới vào ByteArrayOutputStream
// targetDoc.write(out);
// return new ByteArrayInputStream(out.toByteArray());
// } catch (IOException e) {
// e.printStackTrace();
// return null;
// }
// }

// public void convertEmfToPng(String emfFilePath, String outputFilePath) throws
// IOException {
// String command = "convert " + emfFilePath + " " + outputFilePath;
// Process process = Runtime.getRuntime().exec(command);

// try {
// process.waitFor();
// } catch (InterruptedException e) {
// e.printStackTrace();
// }
// }

// public String extractText(MultipartFile file) {
// StringBuilder content = new StringBuilder();

// try (XWPFDocument document = new XWPFDocument(file.getInputStream())) {
// // Đọc tất cả các đoạn văn bản
// for (XWPFParagraph para : document.getParagraphs()) {
// content.append(para.getText()).append("\n");
// }

// // Đọc nội dung trong bảng (nếu có)
// for (XWPFTable table : document.getTables()) {
// for (XWPFTableRow row : table.getRows()) {
// for (XWPFTableCell cell : row.getTableCells()) {
// content.append(cell.getText()).append("\t");
// }
// content.append("\n");
// }
// }
// } catch (IOException e) {
// e.printStackTrace();
// }

// return content.toString();
// }

// // Ghi văn bản vào file DOCX mới
// public ByteArrayInputStream writeDocxWithText(String text, MultipartFile
// file) {
// try (XWPFDocument sourceDoc = new XWPFDocument(file.getInputStream());
// XWPFDocument targetDoc = new XWPFDocument();
// ByteArrayOutputStream out = new ByteArrayOutputStream()) {

// // Copy các phần khác (hình ảnh, bảng biểu) từ tài liệu gốc
// copyPictures(sourceDoc, targetDoc);

// // Thêm văn bản đã xử lý vào file mới
// XWPFParagraph newPara = targetDoc.createParagraph();
// newPara.createRun().setText(text);

// // Ghi file mới vào ByteArrayOutputStream
// targetDoc.write(out);
// return new ByteArrayInputStream(out.toByteArray());
// } catch (IOException e) {
// e.printStackTrace();
// return null;
// }
// }

// // Copy hình ảnh từ tài liệu gốc sang tài liệu mới
// private void copyPictures(XWPFDocument sourceDoc, XWPFDocument targetDoc) {
// List<XWPFPictureData> pictures = sourceDoc.getAllPictures();
// for (XWPFPictureData pic : pictures) {
// try {
// targetDoc.addPictureData(pic.getData(), pic.getPictureType());
// } catch (InvalidFormatException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// }
// }

// }
