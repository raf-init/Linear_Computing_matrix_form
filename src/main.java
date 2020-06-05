package project1;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class main {
	
	static int length(String[] arr1)
	{
		int c1=0;
		
		int i=0;
		
		for(i=0; arr1[i]!=null; i++)
		{
			c1++;
		}
		
		
		return c1;	
	}
	
	private static void readFile1(File fin) throws IOException {
		int maxint=0;
		int minmax=0;
		
		int i=0;
		int j=0;
		int k=0;
		int e,r=0;
		int xcounter=0;
		int arrowtemp=0;
		int tempx=0;
		int tempy=0;
		
		int ptempi=0;
		
		int space=0;
		int tempc2=0;
		int minmaxtemp=0;
		int intflag=0;
		int intflag2=0;
		int symflag=0;
		int wrongsymflag=0;
		int doublexflag=0;
		int flflag=0;
		
		int abcounter=0;
		
		String doublextmp=null;
		String[] doublextemp= new String[10];
		int dxt=0;
		
		String startingerror=null;
		
		int fv=1;
		int dltmp=0;
		int dlflag=0;
		int tempc22=8;
		int wrdflag=0;
		int timec=0;
		int intflag3=0;
		int stflag=0;
		int linec=0;
		int linec2=0;
		int lktemp=0;
		
		int finflag=0;
		int maxpop=0;
		
		String[] stt = new String[10];
		String[] sttemp = new String[100];
		
		int stcounter1=0;
		int stcounter2=0;
		int xscounter=0;
		
		
		String[] C = new String[10];
		String[] B = new String[10];
		String[][] A = new String[10][10];
		String[][] table = new String[10][10];
		String[] x = new String[10];
		String[] Eqin = new String[10];
		
		int eqincounter=0;
		int invcounter=0;
		
		FileInputStream fis = new FileInputStream(fin);
		FileInputStream fis2 = new FileInputStream(fin);
		FileInputStream fis3 = new FileInputStream(fin);
	 
		//Construct BufferedReader from InputStreamReader
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		BufferedReader br2 = new BufferedReader(new InputStreamReader(fis2));
		BufferedReader br3 = new BufferedReader(new InputStreamReader(fis3));
		
		String line = null;
		
		while((line = br.readLine()) != null)
			{	
			
			//finding the right inputted constraints
			
				Scanner st = new Scanner(line);
				if(line.matches("xi>=0 i= (.*)"))
				{
					linec++;
					while(st.hasNext())
					{
						if(st.hasNextInt())
						stt[stcounter1++]=st.next();
						else st.next();
					}
					
				}
				
				
			//finding first line errors
			Scanner fl = new Scanner(line);
			while(fl.hasNext()&&fv==1)
			{
				if(fl.next().matches("\\d+|x(.*)\\d+|min(.*)|max(.*)|\\-|\\+"))
					{
						flflag=0;
					}
				else
					{
						flflag=1;
						break;
					}
			}
			fv=0;
			
			//finding the double < = > in the lines 1-m
			Scanner dl = new Scanner(line);
			while(dl.hasNext())
			{
				if(dl.next().matches("\\=|\\<=|>="))
					{
						dltmp++;
						
					}
				if(dltmp>=2) 
					{
						dlflag=1;
						break;
					}
			}
			dltmp=0;
			
			//finding errors like: <=-14  x4<=
			if((line.contains(" <= ")||line.contains(" = ")||line.contains(" >= ") || line.contains("xi<=") ) && timec>=1)
				{
						wrdflag=0;	
				}
			else if(timec>=1 && linec==0)
				{
					wrdflag=1;
					break;
				}
			
			
			//finding wrong inputted characters like: '";*& etc.
			Scanner t = new Scanner(line);
			while(t.hasNext())
			{
				if(t.next().matches("\\d+|x(.*)\\d+|min(.*)|max(.*)|\\-|\\+|\\<=|\\>=|\\="))
					{
						wrongsymflag=0;
					}
				else if(linec==0)
					{
						wrongsymflag=1;
						break;
					}
			}
			
			//creating the Eqin table
			
			if(line.contains("<="))
			{
				Eqin[eqincounter++]="-1";
			}	
			else if(line.contains(">="))
			{
				Eqin[eqincounter++]="1";
			}
			else if(line.contains("="))
			{
				Eqin[eqincounter++]="0";
			}
			
			
			
			//finding variables that appear more than once in the same line
			Scanner xs = new Scanner(line);
			while(xs.hasNext())
			{
				doublextmp=xs.next();
				if(doublextmp.matches("x(.*)\\d+"))
					{
						doublextemp[dxt++]=doublextmp;
						
					}
			}
			dxt=0;
			for(e=0; doublextemp[e]!=null; e++)
			{
				abcounter=0;
				for(r=0; doublextemp[r]!=null; r++)
				{
					if(doublextemp[e].equals(doublextemp[r]))
					{
						abcounter++;
					}
				}
				
				if(abcounter>=2){ 
					break;
				}
			}
			
			if(abcounter>=2)
			{
				doublexflag=1;
			}
			e=0;
			while(doublextemp[e]!=null)
			{
				doublextemp[e++]=null;
			}
			
			//finding errors like: 2x2 instead of 2 x2
			Scanner u = new Scanner(line);
			while(u.hasNext())
			{
				if(u.next().matches("\\d+(.*)x(.*)\\d+"))
					{
						intflag=1;
						break;
					}
			}
			
			//finding errors like: 2 2 x2
			Scanner v = new Scanner(line);
			while(v.hasNext() && linec==0)
			{
				if(v.next().matches("\\d+") && v.hasNext())
					{
						if(v.next().matches("\\d+") && v.hasNext())
							{
								intflag2=1;
								break;
							}
					}		
			}			
			
			//finding errors like 2 + 2 + x1
			Scanner dn = new Scanner(line);
			while(dn.hasNext() && linec==0)
			{
				if(dn.next().matches("\\d+") && dn.hasNext())
					{
						if(dn.next().matches("\\+") && dn.hasNext())
							{
								if(dn.next().matches("\\d+") && dn.hasNext())
									{
										intflag2=1;
										break;
									}
							}
					}	
			}				
					
			//finding missing coefficient from the variables
			Scanner nn = new Scanner(line);
			while(nn.hasNext() && intflag3==0)
			{
				if(nn.next().matches("\\+|\\-") && nn.hasNext())
					{
						if(nn.next().matches("\\d+"))
							{	
								intflag3=0;
							}			
							else
							{
								intflag3=1;
								break;
							}
					}		
			}			
						
			//finding errors like: -/+ -/+ x2
			Scanner w = new Scanner(line);
			while(w.hasNext())
				{
					if(w.next().matches("[+-]"))
						{
							if(w.next().matches("[+-]"))
								{	symflag=1;
									break;
								}
						}
				}
				timec++;
			}	
		eqincounter=0;
		
		br.close();
		
		//creating the arrays
		while ((line = br2.readLine()) != null) {
			
			if(intflag==1 || intflag2==1 || symflag==1)
				break;
			minmaxtemp=0;
			if(line.substring(0,3).equals("min"))
			{
				minmax = -1;
			}
			else if(line.substring(0,3).equals("max"))
			{
				minmax = 1;
			}
			if(line.startsWith("min") || line.startsWith("max") )
			{
				minmaxtemp=1;
				//creating the table array for the first line of the *.txt file
				Scanner c = new Scanner(line);
				while(c.hasNext())
				{	
					 if(c.hasNextInt())
					{
						table[i][j] = c.next();
						j++;
					}
					else if(c.next().equals("-"))
						{
							table[i][j] = "-"+c.next();
							j++;
						}
				}
				i++;
				j=0;
				
				stcounter2=0;
					
			//creating the x table	
			for(k=0; k<line.length(); k++ )	
			{
				if( line.charAt(k) == 'x' && line.charAt(k-1)!='a') 
				{
			        x[xcounter++]="x" + line.charAt(k+1);
				}
			}
			
			}
			
			//inserting the rest of the numbers in the table array
			if( line.substring(0,1).matches("\\d+") || line.startsWith("-"))
			{
				Scanner a = new Scanner(line);
				while(a.hasNext())
				{
					 if(a.hasNextInt())
					{
						table[i][j] = a.next();
						j++;
					}
					else if(a.next().equals("-"))
						{
							table[i][j] = "-"+a.next();
							j++;
						}
				}
				j=0;
				i++;
			}
			else 
			{
				if(minmaxtemp==0 && linec==0)
					startingerror="Wrong input file";
			}
			
		}
		br2.close();
		
		//filling the C table
		int counter1=0;
	for(j=0; table[0][j]!=null; j++) 
	{
		C[counter1++]=table[0][j];
	}
	j=0;
	
	//finding errors in the line containing the constraints
	if(linec==1)
	{
		for(xscounter=0; x[xscounter]!=null; xscounter++)
			{
				if(length(x)==length(stt))
					{
						stflag=0;
					}
				else 
					{
					stflag=1;
					break;
					}
				if(x[xscounter].contains(stt[xscounter]))
					{
						stflag=0;
					}
				else 
				{
				stflag=1;
				break;
				}
			}
	}
	i=0;
	maxpop=0;
	
	while((line = br3.readLine()) != null)
	{
		if(linec2>=1){
			
		//finding the right A[i][j] depending on the index of the x variables
		Scanner fla = new Scanner(line);
		stcounter2=0;
		while(sttemp[stcounter2]!=null)
		{
			sttemp[stcounter2]=null;
			stcounter2++;
		}
		stcounter2=0;
		while(fla.hasNext())
		{
			sttemp[stcounter2]=fla.next();
			stcounter2++;
		}
		
		stcounter2=0;
		j=0;
		
		while(sttemp[stcounter2]!=null)
		{
			
			if(sttemp[stcounter2].matches("x\\d+"))
			{
				lktemp=Integer.parseInt(sttemp[stcounter2].replaceAll("[\\D]", ""))-1;
				if(maxpop<lktemp)
					maxpop=lktemp;
		
		  				A[i-1][lktemp]=table[i][j];
		  				j++;	
			}
			stcounter2++;
		}
		}
		i++;
		linec2++;
		
	}
	
	br3.close();
	
	//replacing all the null values from the A table
	for(i=0; i<=9; i++)
	{
		for(j=0; j<=9; j++)
		{
			if(A[i][j]==null)
			{
				A[i][j]="0";
			}
		}
	}
	
		//check for wrong input.txt, print C table
	try
	{
	    PrintWriter pr = new PrintWriter("output.txt");
	    //flag checks for wrong input files
	    if (startingerror!=null)
	    		{
	    			pr.print(startingerror);
	    			pr.close();
	    		}
	    pr.print("");
	    if(intflag==1 || intflag2==1 || symflag==1 || doublexflag==1 || wrongsymflag==1 || flflag==1 || dlflag==1 || wrdflag==1 || intflag3==1 || stflag==1 || finflag==1)
	    {
	    	pr.print("Wrong input file");
	    	pr.close();
	    	return;
	    }
	    else if(minmax==-1)
	    	pr.print("min ");
	    else if(minmax==1) 
	    	pr.print("max ");
	    else 
	    {
	    	pr.close();
	    	return;
	    }
	    //printing the C table with the first x variable
	    pr.print("[(");
	    for (i=0; C[i]!=null; i++)
	    {
	        pr.print(C[i] + " ");
	        invcounter++;   
	    }
	    pr.print(")]");
	    pr.format("^T ");
	    
	    pr.format("%s", x[0]);
	    
	    pr.println();
	    //printing the x table
	    for(arrowtemp=1; arrowtemp<=invcounter-1; arrowtemp++)
	    {
	    	pr.print("");
	    	pr.format("           ");
	    	for(tempx=0; C[tempx]!=null; tempx++)
	    	{
	    		for(tempy=1; tempy<=C[tempx].length(); tempy++)
	    		{
	    			pr.print(" ");
	    		}
	    		pr.print(" ");
	    		
	    	}
	    	pr.format("%s", x[arrowtemp]);
	    	pr.println();
	    }
	  	
	  	//filling the B table
	  		int counter2=0;
	  		j=0;
	  		for(i=1; table[i][j]!=null; i++)
	  		{
	  			
	  			for(j=0; table[i][j]!=null; j++)
	  			{
	  				if(table[i][j+1]==null)
	  				{
	  					B[counter2++]=table[i][j];
	  				}
	  			}
	  			j=0;
	  		}
		counter2=0;
	
		
		
		//printing A table, <=,=,>= symbols depending on the Eqin table and the B table
		
pr.println("");
	  	
		for(i=0; i<=linec2-3; i++)
		{
			for(j=0; j<=maxpop; j++)
			{
				for(ptempi=0; ptempi<=linec2-3; ptempi++)
				{
					if(A[ptempi][j].length()>=maxint)
					{
						maxint=A[ptempi][j].length();
					}	
				}
				
				for(space=1; space<=(maxint-A[i][j].length()); space++)
				{
					pr.print(" ");
				}
						
				pr.print(A[i][j] + " ");		
				
			}
			
			if(j-1==maxpop && Eqin[eqincounter]=="-1")
			{
				tempc22=4;
				for(tempc2=tempc22; tempc2>=0; tempc2--)
				{
					pr.print(" ");
				}
				
				pr.print("<=");
				
				for(tempc2=6; tempc2>=B[counter2].length(); tempc2--)
				{
					pr.print(" ");
					
				}
				pr.print(B[counter2++]);
			}
			else if(j-1==maxpop && Eqin[eqincounter]=="0")
			{
				tempc22=5;
				for(tempc2=tempc22; tempc2>=0; tempc2--)
				{
					pr.print(" ");
				}
				
				pr.print("=");
				
				for(tempc2=6; tempc2>=B[counter2].length(); tempc2--)
				{
					pr.print(" ");
					
				}
				pr.print(B[counter2++]);
			}
			else if(j-1==maxpop && Eqin[eqincounter]=="1")
			{
				tempc2=4;
				for(tempc2=tempc22; tempc2>=0; tempc2--)
				{
					pr.print(" ");
				}
				
				pr.print(">=");
				
				for(tempc2=6; tempc2>=B[counter2].length(); tempc2--)
				{
					pr.print(" ");
					
				}
				pr.print(B[counter2++]);
			}
			eqincounter++;
			j=0;
			
			pr.println("");
		}
		pr.println("");
		//printing the x variables referring to the constraints
		for(i=0; i<=xscounter-1; i++)
		{
			if(i==(xscounter-1)/2)
				{
					pr.print(x[i]);
					pr.println("    >=0");
				}
			else pr.println(x[i]);
		}
		 
		
		
	  	
	    pr.close();
	}
	catch (Exception e1)
	{
	    e1.printStackTrace();
	    System.out.println("No such file exists.");
	}
	
	System.out.println();
		
	}
	

	
	
	
	public static void main(String[] args) throws IOException{
		File fin = new File("input.txt");
		readFile1(fin);
		
	}
}
