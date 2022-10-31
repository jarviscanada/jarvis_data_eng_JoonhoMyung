package ca.jrvs.apps.practice;

public class test{

  public static void main(String[] args){
    RegexExcImp regex = new RegexExcImp();
    String filename = "abc.JPEG";

    System.out.println(regex.matchJpeg(filename));

  }

}
