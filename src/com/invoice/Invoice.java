
package com.invoice;

import com.db.DatabaseConnection;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


public class Invoice {
    public static void generateInvoice() throws JRException, ClassNotFoundException, SQLException, IOException
    {
        HashMap hm=null;
        
       String jrxmlFilename="C:/Invoice/invoice.jrxml";
       String pdfFilename="C:/Invoice/invoice.pdf";
       
        JasperDesign jasperDesign = JRXmlLoader.load(jrxmlFilename);
       
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        
        Connection conn = DatabaseConnection.getConnection();
        
        hm=new HashMap();
        
        JasperPrint jprint = JasperFillManager.fillReport(jasperReport, hm, conn);
        
        JasperExportManager.exportReportToPdfFile(jprint,pdfFilename);
        
        File file = new File(pdfFilename);
        Desktop.getDesktop().open(file);
    }
}
