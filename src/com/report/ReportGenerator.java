package com.report;

import com.db.DatabaseConnection;
import com.model.Branch;
import com.model.MembershipPlans;
import com.persistence.PersistBranch;
import com.persistence.PersistMembershipPlans;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javafx.collections.ObservableList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class ReportGenerator {
    public static void genderDistribution() throws SQLException, IOException
    {
        int males,females,nonBinary;
        Connection conn = DatabaseConnection.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "SELECT COUNT(PRN) AS TOTAL FROM CUSTOMER WHERE GENDER='M'";
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        males = rs.getInt("TOTAL");
        sql = "SELECT COUNT(PRN) AS TOTAL FROM CUSTOMER WHERE GENDER='F'";
        rs = stmt.executeQuery(sql);
        rs.next();
        females = rs.getInt("TOTAL");
        sql = "SELECT COUNT(PRN) AS TOTAL FROM CUSTOMER WHERE GENDER='N'";
        rs = stmt.executeQuery(sql);
        rs.next();
        nonBinary = rs.getInt("TOTAL");
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Male", males);
        dataset.setValue("Female", females);
        dataset.setValue("Non-binary", nonBinary);
        JFreeChart chart = ChartFactory.createPieChart("Gender wise distribution", dataset, true, true, false);
        File pieChart = new File("F:/Report/GenderDistribution.jpeg");
        ChartUtilities.saveChartAsJPEG(pieChart, chart, 640, 480);
        System.out.println("Gender wise report ready");
        Desktop.getDesktop().open(pieChart);
    }
    
    public static void ageDistribution() throws SQLException, IOException
    {
        int cat1=0,cat2=0,cat3=0,cat4=0;
        Connection conn = DatabaseConnection.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM CUSTOMER";
        ResultSet rs = stmt.executeQuery(sql);
        Calendar today = Calendar.getInstance();
        int currYear = today.get(Calendar.YEAR);
        int dobYear = 0;
        int age;
        Calendar dob = Calendar.getInstance();
        while(rs.next())
        {
            Date dobDate = rs.getDate("DATEOFBIRTH");
            dob.setTime(dobDate);
            dobYear = dob.get(Calendar.YEAR);
            age = currYear - dobYear;
            if(age<20)
            {
                cat1++;
            }
            else if(age>20 && age<40)
            {
                cat2++;
            }
            else if(age>40 && age<60)
            {
                cat3++;
            }
            else
            {
                cat4++;
            }
        }
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Below 20", cat1);
        dataset.setValue("20-40", cat2);
        dataset.setValue("40-60", cat3);
        dataset.setValue("60 Above", cat4);
        JFreeChart chart = ChartFactory.createPieChart("Age wise distribution", dataset, true, true, false);
        File pieChart = new File("F:/Report/AgeDistribution.jpeg");
        ChartUtilities.saveChartAsJPEG(pieChart, chart, 640, 480);
        System.out.println("Age wise report ready");
        Desktop.getDesktop().open(pieChart);
    }
    
    public static void monthlySales() throws SQLException, IOException
    {
        int months[] ={0,0,0,0,0,0,0,0,0,0,0,0};
        String monthNames[] = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        Connection conn = DatabaseConnection.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM TRANSACTION WHERE EXTRACT(YEAR FROM INVOICEDATE) = (SELECT EXTRACT(YEAR FROM SYSDATE) FROM DUAL)";
        ResultSet rs = stmt.executeQuery(sql);
        Calendar sd = Calendar.getInstance();
        while(rs.next())
        {
            sd.setTime(rs.getTimestamp("INVOICEDATE"));
            months[sd.get(Calendar.MONTH)]+=rs.getDouble("TOTALAMOUNT");
        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i=0;i<12;i++)
        {
            dataset.addValue(months[i], "Sales", monthNames[i]);
        }
        JFreeChart lineChartObj = ChartFactory.createLineChart("Monthly sales", "Month","Sales", dataset, PlotOrientation.VERTICAL, true, true, false);
        File lineChart = new File("F:/Report/MonthlySales.jpeg");
        ChartUtilities.saveChartAsJPEG(lineChart, lineChartObj, 640, 480);
        Desktop.getDesktop().open(lineChart);
    }
    
    public static void branchSales() throws SQLException, IOException
    {
        HashMap<String,Integer> map = new HashMap();
        Branch branch;
        int number;
        ObservableList<Branch> branches = PersistBranch.retrieveAll();
        Iterator<Branch> it = branches.iterator();
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt;
        String sql = "SELECT SUM(TOTALAMOUNT) AS TOTAL FROM TRANSACTION WHERE BRANCH LIKE ?";
        pstmt = conn.prepareStatement(sql);
        while(it.hasNext())
        {
            branch = it.next(); 
            pstmt.setString(1, branch.getName());
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            number = rs.getInt("TOTAL");
            map.put(branch.getName(), number);
        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(Map.Entry<String,Integer> entry : map.entrySet())
        {
            dataset.addValue(entry.getValue(), "Sales", entry.getKey());
        }
        JFreeChart barChartObj = ChartFactory.createBarChart("Branch wise sales", "Branch", "Sales", dataset, PlotOrientation.VERTICAL, true, true, false);
        File barChart = new File("F:/Report/BranchSales.jpeg");
        ChartUtilities.saveChartAsJPEG(barChart, barChartObj, 640, 480);
        Desktop.getDesktop().open(barChart);
    }
    
    public static void planSales() throws SQLException, IOException
    {
        HashMap<String,Integer> map = new HashMap();
        MembershipPlans plan;
        int number;
        ObservableList<MembershipPlans> plans = PersistMembershipPlans.retrieveAll();
        Iterator<MembershipPlans> it = plans.iterator();
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt;
        String sql = "SELECT SUM(TOTALAMOUNT) AS TOTAL FROM TRANSACTION WHERE MEMBERSHIPPLAN LIKE ?";
        pstmt = conn.prepareStatement(sql);
        while(it.hasNext())
        {
            plan = it.next(); 
            pstmt.setString(1, plan.getName());
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            number = rs.getInt("TOTAL");
            map.put(plan.getName(), number);
        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(Map.Entry<String,Integer> entry : map.entrySet())
        {
            dataset.addValue(entry.getValue(), "Sales", entry.getKey());
        }
        JFreeChart barChartObj = ChartFactory.createBarChart("Plan wise sales", "Plan", "Sales", dataset, PlotOrientation.VERTICAL, true, true, false);
        CategoryAxis axis = barChartObj.getCategoryPlot().getDomainAxis();
        axis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        File barChart = new File("F:/Report/PlanSales.jpeg");
        ChartUtilities.saveChartAsJPEG(barChart, barChartObj, 640, 480);
        Desktop.getDesktop().open(barChart);
    }
}
