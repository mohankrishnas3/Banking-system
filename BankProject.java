package bank2;

import java.sql.*;
import java.io.*;

class Combined
  {
    int c1=2004;
    void InsertEmployee()
      {   
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Connection con=null; 
        PreparedStatement pmt=null;
        String query=null;

   try
     {
  	 Class.forName("com.mysql.jdbc.Driver");
  	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
  	
  	
  	 query="insert into branch values(?,?)";
   	 pmt=con.prepareStatement(query);
   	System.out.println("branch name and city");
   	String branch_name=br.readLine();
 	String branch_city=br.readLine();
  	pmt.setString(1,branch_name);
  	pmt.setString(2,branch_city);
  	pmt.executeUpdate();
  	
  	
  	
  	System.out.println("account number");
  	int Acc_no=Integer.parseInt(br.readLine());
  	 query="insert into account values(?,?,?)";
   	 pmt=con.prepareStatement(query);
   	System.out.println("balance");
   	int balance=Integer.parseInt(br.readLine());
   	pmt.setInt(1,Acc_no);
  	pmt.setString(2,branch_name);
  	pmt.setInt(3,balance);
  	pmt.executeUpdate();
  	
  	
  	 query="insert into login values(?,?,?)";
   	 pmt=con.prepareStatement(query);
  	System.out.println("PIN No. =");
  	int en=Integer.parseInt(br.readLine());
  	System.out.println("PASSWORD =");
  	String c=br.readLine();
  	pmt.setInt(1,Acc_no);
  	pmt.setInt(2,en);
  	pmt.setString(3,c);
  	int r=pmt.executeUpdate();
	
		

  		 if(r>0)
   		System.out.println("RECORD INSERTED SUCCESSFULLY");
  	 else
  		System.out.println("ERROR IN INSERTION!!!");
 	 }
   catch(ClassNotFoundException ce)
 	 {
 		  ce.printStackTrace();
 	 }

  catch(SQLException se)
  	{
   		se.printStackTrace();
	  }
   catch(IOException ie)
 	{
  		ie.printStackTrace();
	  }
   }


 void Deleteaccount()
{
          
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        
        Connection con = null;
        PreparedStatement pmt = null;
        PreparedStatement pmt1 = null;
        PreparedStatement pmt2 = null;
        String query = null;
       String query1 = null;
       String query2 = null;
       ResultSet rs=null;

try
 {
	Class.forName("com.mysql.jdbc.Driver");
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
 
	System.out.println("TO DELETE CUSTOMER ACCOUNT, ENTER PIN No. =");
	System.out.println("PIN No. =");
	int en=Integer.parseInt(br.readLine());
	System.out.println("PASSWORD =");
	String p=br.readLine();
	query="select * from login where pin='"+en+"' and password='"+p+"'";
	pmt=con.prepareStatement(query);
	
	rs=pmt.executeQuery(query);
	rs.next();
	int account_number=rs.getInt(1);
	
	
	
	
    query1= "delete from login where pin='"+en+"'and password='"+p+"'";  
    pmt1=con.prepareStatement(query1);
    int r1=pmt1.executeUpdate();
    
    query2= "delete from account where account_number='"+account_number+"'"; 
    pmt2=con.prepareStatement(query2);
    int r2=pmt2.executeUpdate();
 
    
  
 	
      
             //execute query
  
       
      if(r2>0)
   	System.out.println("RECORD DELETED SUCCESSFULLY");
      else
  	System.out.println("ERROR IN DELETION!!!");

 
 }
   	catch(ClassNotFoundException ce)
  	{
   		ce.printStackTrace();
  	}

  	catch(SQLException se)
  	{
 		  se.printStackTrace();
 	 }
	catch(IOException ie)
	{
		ie.printStackTrace();
  }
 }



void UpdateEmployee()
{
	Connection con = null;
	   PreparedStatement pmt = null;
	   PreparedStatement pmt1 = null;
	   String query = null;
	  String query1 = null;
	  ResultSet rs=null;
	  
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	try
	   {

		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");

	   
		System.out.println("PIN No. =");
		int en=Integer.parseInt(br.readLine());
		System.out.println("PASSWORD =");
		String p=br.readLine();
		query="select * from login where pin='"+en+"' and password='"+p+"'";
		pmt=con.prepareStatement(query);
		
		rs=pmt.executeQuery(query);
		rs.next();
		int account_number=rs.getInt(1);
		
		
		
		System.out.println("ENTER AMOUNT TO DEPOSIT=");
		int m=Integer.parseInt(br.readLine());
		//setting values to place holders
	    if(m<=0){
	    	System.out.println("INVALID AMOUNT ENTERED");
	    }
	    else
	    {
		
	  	

	     //execute query
		query1 = "update account set balance=balance+ '"+m+"'where account_number='"+account_number+"'";
		pmt1=con.prepareStatement(query1);

	  
	 
	   
	    
		int r=pmt1.executeUpdate();
		if(r>0)
		System.out.println("AMOUNT DEPOSITED SUCCESSFULLY");
		else
		System.out.println("INSUFFICIENT AMOUNT TO WITHDRAW!!!");
	     
	   
	   }
	   }
		catch(ClassNotFoundException ce)
		{
	 		System.out.println("CLASS NOT FOUND!!!");
			ce.printStackTrace();
		}

		catch(SQLException se)
		{
			se.printStackTrace();
		}
	catch(IOException ie)
	{
		ie.printStackTrace();
	}
    } 


void passchange()
{
   Connection con = null;
   PreparedStatement pmt = null;
  String query = null;
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

try
   {
        
	Class.forName("com.mysql.jdbc.Driver");
   	
   	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
	query = "update login set password=? where account_number=? and password= ?";// here ? is to putany value
	pmt=con.prepareStatement(query);
	
	System.out.println("ACCOUNT NUMBER =");
	int p1=Integer.parseInt(br.readLine());
	System.out.println("OLD PASSWORD=");
	String p=br.readLine();
	System.out.println("NEW PASSWORD=");
        String pa=br.readLine();

	//setting values to place holders

	pmt.setInt(2,p1);
	pmt.setString(1,pa);
	pmt.setString(3,p);
     //execute query

	int r=pmt.executeUpdate();
	if(r>0)
	System.out.println("PASSWORD UPDATED SUCCESSFULLY");
	else
	System.out.println("ERROR IN UPDATION!!!");
       }
	catch(ClassNotFoundException ce)
	{
 		System.out.println("CLASS NOT FOUND!!!");
		ce.printStackTrace();
	}

	catch(SQLException se)
	{
		se.printStackTrace();
	}
catch(IOException ie)
{
	ie.printStackTrace();
}
    }
  
void withdraw()
{
   Connection con = null;
   PreparedStatement pmt = null;
   PreparedStatement pmt1 = null;
   String query = null;
  String query1 = null;
  ResultSet rs=null;
  
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

try
   {

	Class.forName("com.mysql.jdbc.Driver");
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");

   
	System.out.println("PIN No. =");
	int en=Integer.parseInt(br.readLine());
	System.out.println("PASSWORD =");
	String p=br.readLine();
	query="select * from login where pin='"+en+"' and password='"+p+"'";
	pmt=con.prepareStatement(query);
	
	rs=pmt.executeQuery(query);
	rs.next();
	int account_number=rs.getInt(1);
	
	
	
	System.out.println("ENTER AMOUNT TO WITHDRAW=");
	int m=Integer.parseInt(br.readLine());
	//setting values to place holders
    if(m<=0){
    	System.out.println("INVALID AMOUNT ENTERED");
    }
    else
    {
	
  	

     //execute query
	query1 = "update account set balance=balance- '"+m+"'where account_number='"+account_number+"'";
	pmt1=con.prepareStatement(query1);

  
 
   
    
	int r=pmt1.executeUpdate();
	if(r>0)
	System.out.println("AMOUNT WITHDRAWN SUCCESSFULLY");
	else
	System.out.println("INSUFFICIENT AMOUNT TO WITHDRAW!!!");
     
   
   }
   }
	catch(ClassNotFoundException ce)
	{
 		System.out.println("CLASS NOT FOUND!!!");
		ce.printStackTrace();
	}

	catch(SQLException se)
	{
		se.printStackTrace();
	}
catch(IOException ie)
{
	ie.printStackTrace();
}
    }

    void display()
{

   Connection con=null;
   Statement stmt=null;
   ResultSet rs=null;
   String query=null;

try
 {System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
	Class.forName("com.mysql.jdbc.Driver");
   	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
   stmt=con.createStatement();
   query="select * from account";
   rs=stmt.executeQuery(query);
   
   System.out.println("-------------------------------------------CUSTOMER RECORDS------------------------------------------\n\n    ACC_NO                NAME                 BALANCE             \n");
   System.out.println("====================================================================================================="); 
while(rs.next())
        {
   	int account_no=rs.getInt(1);
   	String name=rs.getString(2);
        int bal=rs.getInt(3); 
       
       
                   System.out.printf("%8d   |%18s   |%18d   \n",account_no,name,bal);
   System.out.println("------------------------------------------------------------------------------------------------------");
        }
 }
   catch(ClassNotFoundException ce)
  {
   	ce.printStackTrace();
  }

  catch(SQLException se)
  {
   se.printStackTrace();
  }
}



void aboutus()
{
  System.out.println("\t\tCMS BANK IS INDIA'S LARGEST PRIVATE SECTOR BANK WITH\n\t\tTOTAL ASSETS OF RS. 6,461.29 BILLION (US$ 103 BILLION AT ");
  System.out.println("\t\tMARCH 31, 2015. CMS BANK CURRENTLY HAS A NETWORK OF\n\t\t4,050 BRANCHES AND 12,817 ATM's ACROSS INDIA.");
  System.out.println("\n\n\t\tBOARD MEMBERS\n\t\t-------------\n\t\tCHETHAN R BHAT\n\t\tMOHANKRISHNA\n\t\tSHREYAS M JAIN\n\n");
}




void contact()
{ System.out.println("\t\tCONTACT NO.- 7259188089, 9535444977\n\t\tEMAIL ID.- crbhat16@gmail.com\n\t\tADDRESS- MIT, MANIPAL, KA");

}
}




 class BankProject
{  
      public static void main(String args[])throws IOException
        { 
 	int chc=1;
 	 BufferedReader stdin= new BufferedReader (new InputStreamReader (System.in));
   	Combined c=new Combined();
 	while (chc==1)
	{        System.out.println("\n\t =* = * = * = * = * = * = * =* = * = * = * = * = * = * =* = * = * =* = * =* = * ");
                 System.out.println("\t||                                                                           ||");
                 System.out.println("\t||-------------------------WELCOME TO CMS BANK-----------------------||");
                 System.out.println("\t||                                                                           ||");
                 System.out.println("\t =* = * = * = * = * = * = * =* = * = * = * = * = * = * =* = * = * = * = * =* = *\n\n\n\n");



                System.out.println("                        *+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*");
                System.out.println("     \t\t\t*                               *");
                System.out.println("     \t\t\t*              MENU             *");
                System.out.println("     \t\t\t*                           *");
                System.out.println("     \t\t\t*                               *");
  		System.out.println("     \t\t\t*\t1. NEW ACCOUNT          *\n     \t\t\t*\t2. DELETE ACCOUNT       *\n     \t\t\t*\t3. DEPOSIT AMOUNT       *");
                System.out.println("     \t\t\t*\t4. WITHDRAW             *\n     \t\t\t*\t5. DISPLAY INFO         *\n    \t\t\t*\t6. CHANGE PASSWORD      *\n    \t\t\t*\t7. ABOUT US             *\n    \t\t\t*\t8. CONTACT US           *");
  		System.out.println("     \t\t\t*                               *");
                System.out.println("                        *+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*+*\n\n");
      
              System.out.println("\t\tENTER YOUR CHOICE: =");
		int ch= Integer.parseInt(stdin.readLine());
  		System.out.println();

		  switch(ch)
		{ 
  			 case 1:  
 			 c.InsertEmployee();
  			 break;
  			 case 2:
  			 c.Deleteaccount();
 	        	 break;		
			
			case 3:
  			c.UpdateEmployee();
 			 break;
			
			case 4:
  			c.withdraw();
 			 break;

			  case 5:
 			  c.display();
  			 break;
                          case 6:
                          c.passchange();
                          break;
                          case 7:
                          c.aboutus();
                          break;
                          case 8:
                          c.contact();
                          break;
  			default:
               			System.out.println("wrong choice!!!!!");
		}
    	System.out.println("\n\nENTER 1 TO REPEAT PROCEDURE ELSE ENTER 0\n\nTHANK YOU! FOR CHOOSING CMS BANK\n");
   System.out.println("choice =");
  	chc=Integer.parseInt(stdin.readLine());
 
	}
       }
}









