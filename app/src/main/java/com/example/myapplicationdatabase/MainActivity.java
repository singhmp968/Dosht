package com.example.myapplicationdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
private TextView textView;
private static  String ip = "192.168.1.6";
private static String port = "1443";
private static  String Classes = "net.sourceforgr.jtdc.jdbc.Driver";
private static String database = "testDatabase1";
private static String username = "test";
private static String password = "root";
private static String url = "jdbd:jtds:sqlserver://" +ip+":"+ port + "/" + database;

private Connection connection  = null;


@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
textView = findViewById(R.id.pf_textView);

    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    StrictMode.setThreadPolicy(policy);

    try {
        Class.forName(Classes);
        connection = DriverManager.getConnection(url,username,password);
        textView.setText("SUCESS");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    textView.setText("Error");
    } catch (SQLException e) {
        e.printStackTrace();
        textView.setText("FAILURE");
    }
}


    public void sqlButton(View view) {
if(connection!=null){
    Statement statement = null;
    try {
        statement = connection.createStatement();
        ResultSet resultset =statement.executeQuery("SELECT textbox from inbox where pr = 'raf'");
    while(resultset.next()){
        textView.setText(resultset.getString(1));
    }
    } catch (SQLException e) {
        e.printStackTrace();
    }

}else {
    textView.setText("Connection is failed");
}
    }

    public void sendmessage(String pf_id,int stext) throws SQLException {

        if (connection != null) {
            Statement statement = null;
            PreparedStatement pstmt = null;

            statement = connection.createStatement();
            pstmt = (PreparedStatement) connection.createStatement();
            String sql = "UPDATE INBOX SET  TEXTBOX- ? WHERE PR =?";
            pstmt.setInt(1, stext);
            pstmt.setString(2, pf_id);
            if (pstmt.executeUpdate() == 1) {
                System.out.println("Execured Sucess");
                //return 1;
            }

        }
        //return 0;
    }
    public Stock getStock(String productID) throws SQLException {
        Stock st=new Stock();
        DBUtil db=new DBUtil();
        Connection conn =null;
        PreparedStatement pstmt =null;
        conn=db.getConnection();
//*****************************
        String sql ="select * from TBL_STOCK WHERE PRODUCT_ID =?";
        pstmt =conn.prepareStatement(sql);
        pstmt.setString(1, productID);
        ResultSet rs=pstmt.executeQuery();
        rs.next();
        //*****************************
//	String ProductId=rs.getString(1);
//	 String ProductName = rs.getString(2);
//	 int QuantityOnHand=rs.getInt(3);
//	 double ProductUnitPrice=rs.getDouble(4);
//	 int recordLevel=rs.getInt(5);
        //*****************************
        st.setProductId(rs.getString(1));
        st.setProductName(rs.getString(2));
        st.setQuantityOnHand(rs.getInt(3));
        st.setProductUnitPrice(rs.getDouble(4));
        st.setRecordLevel(rs.getInt(5));
//	ArrayList<Stock> li=new ArrayList<>();
//	li.add(st);
//

        return st;

    }


        }
