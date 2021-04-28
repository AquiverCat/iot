package com.genius.controller;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import io.swagger.annotations.Api;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@Api(value = "测试接口", tags = {"用于测试的接口"})
@RestController
public class HelloController {


    private final static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    public Object hello() throws Exception{
        List<InputStream> inputStreamList = new ArrayList<InputStream>();
        File inputWord1 = new File("D:\\test\\1.docx");
        InputStream docxInputStream1 = new FileInputStream(inputWord1);
        inputStreamList.add(docxInputStream1);
        //字节数组形式存储需要合并的文档
        List<byte[]> inputStreamByteList = new ArrayList<byte[]>();
        //自动生成封皮
        inputStreamByteList.add(newCoverPdf("自动生成封皮").toByteArray());
        //自动生成表格
        inputStreamByteList.add(newTablePdf("审批单").toByteArray());

        for(InputStream inputStream:inputStreamList){
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            docxToPdfNew(inputStream,bOut);
            inputStreamByteList.add(bOut.toByteArray());
        }

        merge(inputStreamByteList,"D:\\test\\合并输出文档.pdf");

        return "SUCCESS";
    }

    public ByteArrayOutputStream newCoverPdf(String name) throws DocumentException, IOException {
        //创建文件
        Document document = new Document(PageSize.A4, 80, 80, 50, 50);
        //建立一个书写器
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, byteArrayOutputStream);
        //打开文件
        document.open();
        //中文字体,解决中文不能显示问题
        BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
        //黑色字体
        Font blueFont = new Font(bfChinese);
        blueFont.setColor(BaseColor.BLACK);
        blueFont.setSize(30);
        //段落文本
        Paragraph paragraphBlue = new Paragraph(name, blueFont);
        paragraphBlue.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraphBlue);
        //插入空行
        for(int i=0;i<6;i++){
            document.add(Chunk.NEWLINE);
        }
        //黑色字体
        Font blueFont1 = new Font(bfChinese);
        blueFont1.setColor(BaseColor.BLACK);
        blueFont1.setSize(15);
        //编制单位
        Paragraph paragraphBlue1 = new Paragraph("编制单位：鄂尔多斯电业局", blueFont1);
        paragraphBlue1.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraphBlue1);
        //插入空行
        for(int i=0;i<12;i++){
            document.add(Chunk.NEWLINE);
        }
        //编制人
        Paragraph paragraphBlue2 = new Paragraph("编制人：施工单位项目负责人", blueFont1);
        paragraphBlue2.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraphBlue2);
        //时间
        SimpleDateFormat sdfTitlePage = new SimpleDateFormat("yyyy年MM月dd日");
        Paragraph paragraphBlue3 = new Paragraph(sdfTitlePage.format(new Date()), blueFont1);
        paragraphBlue3.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraphBlue3);
        //黑色字体
        Font greenFont = new Font(bfChinese);
        greenFont.setColor(BaseColor.BLACK);
        greenFont.setSize(15);
        //创建章节
        Paragraph chapterTitle = new Paragraph("资料清单", blueFont);
        chapterTitle.setAlignment(Element.ALIGN_CENTER);
        //插入空行
        for(int i=0;i<3;i++){
            document.add(Chunk.NEWLINE);
        }
        Chapter chapter1 = new Chapter(chapterTitle, 1);
        chapter1.setNumberDepth(0);
        //添加列表
        for(int i=1;i<14;i++){
            Paragraph sectionTitle = new Paragraph("附件"+i, greenFont);
            sectionTitle.setFirstLineIndent(100);
            Section section = chapter1.addSection(sectionTitle);
        }
        //将章节添加到文章中
        document.add(chapter1);
        //关闭文档
        document.close();
        //关闭书写器
        writer.close();
        return byteArrayOutputStream;
    }

    public ByteArrayOutputStream newTablePdf(String name) throws Exception {
        Document document = new Document(PageSize.A4, 80, 80, 50, 50);
        //建立一个书写器
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();
        //中文字体,解决中文不能显示问题
        BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
        //黑色字体
        Font blueFont = new Font(bfChinese);
        blueFont.setColor(BaseColor.BLACK);
        blueFont.setSize(30);
        //添加内容
        Paragraph paragraph = new Paragraph(name, blueFont);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);
        //创建有两列的table
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100); // 宽度100%填充
        table.setSpacingBefore(40f); // 前间距
        table.setSpacingAfter(10f); // 后间距
        //生成表格行
        createRow(table,"工程名称","名称1");
        createRow(table,"工程承包单位","名称1");
        createRow(table,"工程内容","名称1");
        createRow(table,"工程地点","名称1");
        createRow(table,"工程起止时间","2020年11月08日至 2020年11月10日","作业人数","12");
        createRow(table,"审核内容");
        createRow(table,"审核单位","审核意见","审核人");
        for(int i=1;i<17;i++) {
            createRow(table, "审核单位" + i, "同意", "审核人" + i);
        }
        document.add(table);
        //关闭文档
        document.close();
        //关闭书写器
        writer.close();
        return byteArrayOutputStream;
    }

    private void createRow(PdfPTable table,String oneCellText) throws Exception{
        PdfPCell cell11=new PdfPCell();
        cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell11.setPhrase(new Paragraph(oneCellText,getPdfChineseFont()));
        cell11.setRowspan(1);
        cell11.setColspan(4);//合并单元格
        table.addCell(cell11);
    }

    private void createRow(PdfPTable table,String oneCellText, String twoCellText) throws Exception{
        PdfPCell cell11=new PdfPCell();
        cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell11.setPhrase(new Paragraph(oneCellText,getPdfChineseFont()));
        cell11.setRowspan(1);
        cell11.setColspan(1);//合并单元格
        table.addCell(cell11);
        PdfPCell cell12=new PdfPCell();
        cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell12.setPhrase(new Paragraph(twoCellText,getPdfChineseFont()));
        cell12.setRowspan(1);
        cell12.setColspan(3);//合并单元格
        table.addCell(cell12);
    }

    private void createRow(PdfPTable table,String oneCellText, String twoCellText, String threeCellText) throws Exception{
        PdfPCell pCell1 = new PdfPCell();
        pCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        pCell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pCell1.setPhrase(new Paragraph(oneCellText,getPdfChineseFont()));
        table.addCell(pCell1);
        PdfPCell pCell2 = new PdfPCell();
        pCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        pCell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pCell2.setPhrase(new Paragraph(twoCellText,getPdfChineseFont()));
        pCell2.setRowspan(1);
        pCell2.setColspan(2);
        table.addCell(pCell2);
        PdfPCell pCell3 = new PdfPCell();
        pCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        pCell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pCell3.setPhrase(new Paragraph(threeCellText,getPdfChineseFont()));
        table.addCell(pCell3);
    }

    private void createRow(PdfPTable table,String oneCellText, String twoCellText, String threeCellText,String fourCellText) throws Exception{
        PdfPCell cell1 = new PdfPCell();
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell1.setPhrase(new Paragraph(oneCellText,getPdfChineseFont()));
        table.addCell(cell1);
        PdfPCell cell2 = new PdfPCell();
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell2.setPhrase(new Paragraph(twoCellText,getPdfChineseFont()));
        table.addCell(cell2);
        PdfPCell cell3 = new PdfPCell();
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell3.setPhrase(new Paragraph(threeCellText,getPdfChineseFont()));
        table.addCell(cell3);
        PdfPCell cell4 = new PdfPCell();
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell4.setPhrase(new Paragraph(fourCellText,getPdfChineseFont()));
        table.addCell(cell4);
    }

    public static String merge(List<byte[]> inputStreamByteList, String fileOutputPathAndName) {
        logger.info("-----------------------------pdf合并--------------------------------------");
        long start = System.currentTimeMillis();
        Document document = new Document();
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(inputStreamByteList.get(0));
            document = new Document(new PdfReader(byteArrayInputStream).getPageSize(1));
            PdfCopy copy = new PdfCopy(document, new FileOutputStream(new File(fileOutputPathAndName)));
            copy.close();
            document.open();
            for (byte[] inputStream : inputStreamByteList) {
                ByteArrayInputStream byteArrayInputStream1 = new ByteArrayInputStream(inputStream);
                PdfReader reader = new PdfReader(byteArrayInputStream1);
                int num = reader.getNumberOfPages();
                for (int i = 1; i <= num; i++) {
                    document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader, i);
                    copy.addPage(page);
                }
                reader.close();
            }
            long end = System.currentTimeMillis();
            logger.info("------------------------pdf合并完成，用时：" + (end - start) + "ms--------------------------");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("----------------------pdf合并失败,异常信息:" + e.getMessage() + "--------------------------");
        } finally {
            document.close();
            logger.info("-------------------------------------流关闭-------------------------------------------");
        }
        return fileOutputPathAndName;
    }

    public OutputStream docxToPdf(InputStream docxInputStream,OutputStream outputStream) throws Exception{

        IConverter converter = LocalConverter.builder().build();
        converter.convert(docxInputStream).as(DocumentType.DOCX).to(outputStream).as(DocumentType.PDF).execute();
        logger.info("-----------------------------docx to pdf success--------------------------------------");
        return outputStream;
    }

    public OutputStream docxToPdfNew(InputStream docxInputStream,OutputStream outputStream) throws IOException{
        XWPFDocument xwpfDocument = new XWPFDocument(docxInputStream);
        PdfOptions pdfOptions = PdfOptions.create();
        PdfConverter.getInstance().convert(xwpfDocument,outputStream,pdfOptions);
        docxInputStream.close();
        outputStream.close();
        return outputStream;
    }

    public static Font getPdfChineseFont() throws Exception {
        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H",
                BaseFont.NOT_EMBEDDED);
        Font fontChinese = new Font(bfChinese, 12, Font.NORMAL);
        return fontChinese;
    }

}
