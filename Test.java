import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Test{
  public static String stringToBinary(String text){
    String bString="";
    String temp="";
    for(int i=0;i<text.length();i++){
      temp=Integer.toBinaryString(text.charAt(i));
      for(int j=temp.length();j<8;j++){
        temp="0"+temp;
      }
      bString+=temp+" ";
    }
    return bString;
  }

  public static String binaryToDecimal(String receivedBinary){  
    String eachChar[] = receivedBinary.split(" ");
    String finalString = "";
    int decimal = 0;
    int i , j;
    for(String eachStr:eachChar){
      decimal = 0;
      for(i = eachStr.length() - 1, j = 0; i >=0; i --,j++){
        char tar = eachStr.charAt(i);
        if(tar == '1'){
          decimal += Math.pow(2, j);
        }
      }
      finalString += Integer.toString(decimal) + " ";
    }
    return finalString;
  }
  public static String decimalToBinary(String receivedDecimal){  
    String eachChar[] = receivedDecimal.split(" ");
    String finalString = "";
    String tempString;
    int decimal = 0;
    int i , j;
    int binary;
    for(String eachStr:eachChar){
      tempString = "";
      decimal = Integer.parseInt(eachStr);
      for(i = 0; i < 8; i ++){
        binary = decimal % 2;
        decimal /= 2;
        tempString += binary;
      }
      tempString = new StringBuilder(tempString).reverse().toString();
      finalString += tempString + " ";
    }
    return finalString;
  }  
  public static String BinaryToString(String receivedBinary){
    String[] code = receivedBinary.split(" ");
    String word="";
    for(int i=0;i<code.length;i++){
      word+= (char)Integer.parseInt(code[i],2);
    }
    System.out.println(word);
    return word;
  }
 public static void main(String []args) throws FileNotFoundException 
{
   int decimal = 0;  
   int i = 0, remainder;
   String word, receivedBinary, receivedDecimal;
   File file = new File("input.txt");
   Scanner sc = new Scanner(file);

   while(sc.hasNext()){
    word=sc.nextLine();
    System.out.println("The input is : "+ word);
    receivedBinary=stringToBinary(word);
    System.out.println("The Binary  Version is : "+ receivedBinary);
    receivedDecimal = binaryToDecimal(receivedBinary);
    System.out.println("The Decimal  Version is : "+ receivedDecimal);
    receivedBinary = decimalToBinary(receivedDecimal);
    System.out.println("The Decimal to binary " + receivedBinary);
    word = BinaryToString(receivedBinary);
    System.out.println("The Final text is " + word + "\n\n");
  }
 }


/*public static int binaryToDecimal(String receivedBinary) 
{  
     
        while (receivedBinary!=0)  
        {  
            remainder = receivedBinary%10;  
            receivedBinary /= 10;  
            decimal += remainder*Math.pow(2,i);  
            i++;  
        }  
        return decimal;  
}

public static String decimalToBinary(String receivedDecimal) 
{  
        String binary = new String();  
        if (receivedDecimal >= 0) 
           {  
            while (receivedDecimal != 0)
              {  
                binary = (receivedDecimal % 2) + binary;  
                receivedDecimal /= 2;  
              }  
            }  
        return binary;  
}  
 public static String BinaryToString(String receivedBinary)
 {
     String[] code = receivedBinary.split(" ");
     String word="";
     for(int i=0;i<code.length;i++)
     {
         word+= (char)Integer.parseInt(code[i],2);
     }
     System.out.println(word);
     return word;
 }*/
}