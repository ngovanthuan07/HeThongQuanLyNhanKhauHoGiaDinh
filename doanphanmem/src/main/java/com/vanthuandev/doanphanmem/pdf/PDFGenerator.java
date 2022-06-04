package com.vanthuandev.doanphanmem.pdf;

import com.vanthuandev.doanphanmem.pojos.NhanKhauThuongTru;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PDFGenerator {
    private List<NhanKhauThuongTru> nhanKhauThuongTrus;

    public void generateNhanKhauThuongTru(HttpServletResponse response, Map<String, String> map) {
      try {
          // Creating the Object of Document
          Document document = new Document(PageSize.A4);

          // Getting instance of PdfWriter
          PdfWriter.getInstance(document, response.getOutputStream());


          // Opening the created document to modify it
          document.open();

          // Creating font
          // Setting font style and size
          Font fontTiltle = FontFactory.getFont(FontFactory.defaultEncoding);
          fontTiltle.setSize(20);

          // Creating paragraph
          Paragraph paragraph = new Paragraph("Danh sách nhân khẩu thường trú", fontTiltle);

          // Aligning the paragraph in document
          paragraph.setAlignment(Paragraph.ALIGN_CENTER);

          // Adding the created paragraph in document
          document.add(paragraph);

          // Thêm nội dung
          if(map.get("dangKyXaThuongTru") != null && !map.get("dangKyXaThuongTru") .isEmpty()) {
              Font fontTiltle2 = FontFactory.getFont(FontFactory.defaultEncoding);
              fontTiltle2.setSize(16);

              // Creating paragraph
              Paragraph paragraph2 = new Paragraph(map.get("dangKyXaThuongTru"), fontTiltle2);

              // Aligning the paragraph in document
              paragraph2.setAlignment(Paragraph.ALIGN_CENTER);

              // Adding the created paragraph in document
              document.add(paragraph2);
          }
          if(map.get("dangKyTuNgay") != null
             && !map.get("dangKyTuNgay").isEmpty()
             && map.get("dangKyTuNgay") != null
             && !map.get("dangKyDenNgay").isEmpty()) {
              Font fontTiltle3 = FontFactory.getFont(FontFactory.defaultEncoding);
              fontTiltle3.setSize(13);
              Paragraph paragraph3 = new Paragraph(String.format("Đăng ký từ ngày: %s đến ngày %s", map.get("dangKyTuNgay"), map.get("dangKyDenNgay")));
              paragraph3.setAlignment(Paragraph.ALIGN_LEFT);
              document.add(paragraph3);
          }
          // end

          // Creating a table of 3 columns
          PdfPTable table = new PdfPTable(9);

          // Setting width of table, its columns and spacing
          table.setWidthPercentage(100f);
         // table.setWidths(new int[] { 1, 1, 2, 2, 4, 2, 1, 2, 2});
          table.setSpacingBefore(5);

          // Create Table Cells for table header
          PdfPCell cell = new PdfPCell();

          // Setting the background color and padding
          cell.setBackgroundColor(CMYKColor.LIGHT_GRAY);
          cell.setPadding(5);
          cell.setHorizontalAlignment(1);

          // Creating font
          // Setting font style and size
          Font font = FontFactory.getFont(FontFactory.defaultEncoding);
          font.setColor(CMYKColor.WHITE);

          // Adding headings in the created table cell/ header
          // Adding Cell to table
          cell.setPhrase(new Phrase("STT", font));
          table.addCell(cell);

          cell.setPhrase(new Phrase("Số hộ khẩu", font));
          table.addCell(cell);


          cell.setPhrase(new Phrase("Họ và tên", font));
          table.addCell(cell);

          cell.setPhrase(new Phrase("CMND", font));
          table.addCell(cell);

          cell.setPhrase(new Phrase("Nơi thường trú", font));
          table.addCell(cell);

          cell.setPhrase(new Phrase("Dân tộc", font));
          table.addCell(cell);

          cell.setPhrase(new Phrase("Giới tính", font));
          table.addCell(cell);

          cell.setPhrase(new Phrase("Ngày sinh", font));
          table.addCell(cell);

          cell.setPhrase(new Phrase("Ngày đăng ký thường trú", font));
          table.addCell(cell);

          // Iterating over the list of students
          int couter = 0;
          for (NhanKhauThuongTru nhanKhauThuongTru : nhanKhauThuongTrus) {
              ++couter;
              // 1
              table.addCell(String.valueOf(couter));
              // 2
              table.addCell(String.valueOf(nhanKhauThuongTru.getSoHoKhau().getSoHK()));
              // 3
              table.addCell(nhanKhauThuongTru.getNhanKhau().getHoVaTen());
              // 4
              table.addCell(nhanKhauThuongTru.getNhanKhau().getCmnd());
              // 5
              table.addCell(nhanKhauThuongTru.getSoHoKhau().getNoiDangKyThuongTru());
              // 6
              table.addCell(nhanKhauThuongTru.getNhanKhau().getDanToc().getTenDT());
              // 7
              table.addCell(nhanKhauThuongTru.getNhanKhau().getGioiTinh() == 1 ? "Nam" : "Nữ");
              // 8
              table.addCell(nhanKhauThuongTru.getNhanKhau().getNgaySinh().toString());
              // 9
              table.addCell(nhanKhauThuongTru.getNhanKhau().getNgayDKTT().toString());
          }
          // Adding the created table to document
          document.add(table);

          // Closing the document
          document.close();
      } catch (IOException e) {
          e.printStackTrace();
      }
    }
}
