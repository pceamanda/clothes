package com.fiap.frameworks.clothes.async;

import com.fiap.frameworks.clothes.entity.SaleEntity;
import com.fiap.frameworks.clothes.entity.SaleProductEntity;
import com.fiap.frameworks.clothes.response.SaleProductResponse;
import com.fiap.frameworks.clothes.response.SaleResponse;
import com.fiap.frameworks.clothes.utils.Utils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class PDFMaker {

    public static void generateInvoicePDF(SaleResponse sale) {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Document document = new Document();

        try {
            String fileName = "src/test/resources/cupons/invoice_" + sale.getId() + ".pdf";
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            // adicionando um paragrafo no documento
            Paragraph nm = new Paragraph("FIAP ROUPAS");
            nm.setAlignment(Element.ALIGN_CENTER);
            document.add(nm);
            Paragraph addr = new Paragraph("Av. Lins de Vasconcelos, 383 - Aclimação");
            addr.setAlignment(Element.ALIGN_CENTER);
            document.add(addr);
            Paragraph stt = new Paragraph("São Paulo / SP");
            stt.setAlignment(Element.ALIGN_CENTER);
            document.add(stt);

            document.add(new Paragraph("CPF/CNPJ: " + Utils.formatCPF(sale.getCustomerCPF())));
            document.add(new Paragraph("IE: 222.222.222.222"));
            document.add(new Paragraph("IM: 3.333.333-3"));

            document.add(new Paragraph(" "));
            DottedLineSeparator dottedline = new DottedLineSeparator();
            document.add(dottedline);
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(4);
            PdfPCell cell = new PdfPCell(new Phrase(sale.getDate().format(formatters)));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("10:01:01"));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("CCF: 010333"));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("COO: " + sale.getId()));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            table.setWidthPercentage(100);
            document.add(table);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("CUPOM FISCAL"));
            document.add(new Paragraph(" "));

            Font tblheadfont = new Font();
            tblheadfont.setSize(8.5f);

            PdfPTable tbh = new PdfPTable(9);
            tbh.setWidths(new float[] { 5, 10, 40, 5, 5, 15, 5, 5, 10 });

            PdfPCell itemCell = new PdfPCell(new Phrase("ITEM", tblheadfont));
            tbh.addCell(itemCell);

            PdfPCell codCell = new PdfPCell(new Phrase("CODIGO", tblheadfont));
            tbh.addCell(codCell);

            PdfPCell desCell = new PdfPCell(new Phrase("DESCRICAO", tblheadfont));
            tbh.addCell(desCell);

            PdfPCell qtCell = new PdfPCell(new Phrase("QTD.", tblheadfont));
            tbh.addCell(qtCell);

            PdfPCell unCell = new PdfPCell(new Phrase("UN.", tblheadfont));
            tbh.addCell(unCell);

            PdfPCell vluCell = new PdfPCell(new Phrase("VL UNIT.(R$)", tblheadfont));
            tbh.addCell(vluCell);

            PdfPCell stCell = new PdfPCell(new Phrase("ST", tblheadfont));
            tbh.addCell(stCell);

            PdfPCell vlCell = new PdfPCell(new Phrase("VL", tblheadfont));
            tbh.addCell(vlCell);

            PdfPCell itCell = new PdfPCell(new Phrase("ITEM(R$)", tblheadfont));
            tbh.addCell(itCell);

            tbh.setWidthPercentage(100);
            document.add(tbh);

            LineSeparator ls = new LineSeparator();
            document.add(ls);

            // loop items
            int i = 1;
            for (SaleProductResponse item : sale.getSaleProductsResponse()) {

                PdfPTable tbhi = new PdfPTable(9);
                tbhi.setWidths(new float[] { 5, 10, 40, 5, 5, 15, 5, 5, 10 });

                PdfPCell cell7 = new PdfPCell(new Phrase(i + "", tblheadfont));
                tbhi.addCell(cell7);

                PdfPCell cell8 = new PdfPCell(new Phrase(item.getProductId() + "", tblheadfont));
                tbhi.addCell(cell8);

                PdfPCell cell9 = new PdfPCell(new Phrase(item.getProductName(), tblheadfont));
                tbhi.addCell(cell9);

                PdfPCell cell1 = new PdfPCell(new Phrase(item.getAmount() + "", tblheadfont));
                tbhi.addCell(cell1);

                PdfPCell cell2 = new PdfPCell(new Phrase(" ", tblheadfont));
                tbhi.addCell(cell2);

                PdfPCell cell3 = new PdfPCell(new Phrase(String.format("%.2f", item.getPrice()), tblheadfont));
                tbhi.addCell(cell3);

                PdfPCell cell4 = new PdfPCell(new Phrase(" ", tblheadfont));
                tbhi.addCell(cell4);

                PdfPCell cell5 = new PdfPCell(new Phrase(" ", tblheadfont));
                tbhi.addCell(cell5);

                PdfPCell cell6 = new PdfPCell(new Phrase(" ", tblheadfont));
                tbhi.addCell(cell6);

                tbhi.setWidthPercentage(100);
                document.add(tbhi);
                i++;
            }

            // display total
            document.add(new Paragraph(" "));
            ls.setPercentage(50);
            ls.setAlignment(LineSeparator.ALIGN_RIGHT);
            document.add(ls);
            PdfPTable total = new PdfPTable(2);

            PdfPCell strTo = new PdfPCell(new Phrase("TOTAL R$"));
            total.addCell(strTo);

            PdfPCell to = new PdfPCell(new Phrase(String.format("%.2f", sale.getFullPrice())));
            to.setHorizontalAlignment(Element.ALIGN_RIGHT);
            total.addCell(to);

            total.setWidthPercentage(100);
            document.add(total);

            // HASH
            document.add(new Paragraph(" "));
            document.add(new Paragraph(sale.getHash()));

            // dados da empresa
            document.add(new Paragraph(" "));
            document.add(new Paragraph("FIAP ROUPAS S/A MF. 4000 178 M1 ECF42"));
            document.add(new Paragraph("VERSÃO 01.00.01	ECF: 8233	LJ: 8233"));
            document.add(new Paragraph("QQQQQQQQQQQRTV 12/06/2018	20:01:56"));
            document.add(new Paragraph("FAB: BE000000000000000000000000 00"));

            // copyright
            document.addSubject("Gerando PDF em Java");
            document.addKeywords("www.fiap.com.br");
            document.addCreator("by 30SCJ");
            document.addAuthor("Fiap Team - Amanda, Evair, Lucas, Stiven");

        } catch (DocumentException | IOException de) {
            System.err.println(de.getMessage());
            throw new RuntimeException();
        } finally {
            document.close();
        }

    }
}

