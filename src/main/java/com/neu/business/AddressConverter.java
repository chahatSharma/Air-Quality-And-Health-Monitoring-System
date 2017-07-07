package com.neu.business;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chahatSharma
 */
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.codehaus.jackson.map.ObjectMapper;


public class AddressConverter {
 /*
  * Geocode request URL. Here see we are passing "json" it means we will get
  * the output in JSON format. You can also pass "xml" instead of "json" for
  * XML output. For XML output URL will be
  * "http://maps.googleapis.com/maps/api/geocode/xml";
  */

 private static final String URL = "http://maps.googleapis.com/maps/api/geocode/json";

 /*
  * Here the fullAddress String is in format like
  * "address,city,state,zipcode". Here address means "street number + route"
  * .
  */
 public GoogleResponse convertToLatLong(String fullAddress) throws IOException {

  /*
   * Create an java.net.URL object by passing the request URL in
   * constructor. Here you can see I am converting the fullAddress String
   * in UTF-8 format. You will get Exception if you don't convert your
   * address in UTF-8 format. Perhaps google loves UTF-8 format. :) In
   * parameter we also need to pass "sensor" parameter. sensor (required
   * parameter) — Indicates whether or not the geocoding request comes
   * from a device with a location sensor. This value must be either true
   * or false.
   */
  URL url = new URL(URL + "?address="
    + URLEncoder.encode(fullAddress, "UTF-8") + "&sensor=false");
  // Open the Connection
  URLConnection conn = url.openConnection();

  InputStream in = conn.getInputStream() ;
  ObjectMapper mapper = new ObjectMapper();
  GoogleResponse response = (GoogleResponse)mapper.readValue(in,GoogleResponse.class);
  in.close();
  return response;
  

 }
 
 public GoogleResponse convertFromLatLong(String latlongString) throws IOException {

  /*
   * Create an java.net.URL object by passing the request URL in
   * constructor. Here you can see I am converting the fullAddress String
   * in UTF-8 format. You will get Exception if you don't convert your
   * address in UTF-8 format. Perhaps google loves UTF-8 format. :) In
   * parameter we also need to pass "sensor" parameter. sensor (required
   * parameter) — Indicates whether or not the geocoding request comes
   * from a device with a location sensor. This value must be either true
   * or false.
   */
  URL url = new URL(URL + "?latlng="
    + URLEncoder.encode(latlongString, "UTF-8") + "&sensor=false");
  // Open the Connection
  URLConnection conn = url.openConnection();

  InputStream in = conn.getInputStream() ;
  ObjectMapper mapper = new ObjectMapper();
  GoogleResponse response = (GoogleResponse)mapper.readValue(in,GoogleResponse.class);
  in.close();
  return response;
  

 }

 
// public static void main(String[] args) throws IOException {
//  
//  //GoogleResponse res = new AddressConverter().convertToLatLong("Apollo Bunder,Mumbai ,Maharashtra, India");
//  GoogleResponse res = new AddressConverter().convertToLatLong("02139");
//     if(res.getStatus().equals("OK"))
//  {
//   for(Result result : res.getResults())
//   {
//    System.out.println("Lattitude of address is :"  +result.getGeometry().getLocation().getLat());
//    System.out.println("Longitude of address is :" + result.getGeometry().getLocation().getLng());
//    System.out.println("Location is " + result.getGeometry().getLocation_type());
//    JFrame test = new JFrame("Google Maps");
//
//        try {
//            String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center=" + 
//
//                     result.getGeometry().getLocation().getLat() + "," + result.getGeometry().getLocation().getLng() +"&zoom=11&size=612x612&scale=2&maptype=hybrid&path=color:red|weight:1|fillcolor:yellow";
//            
////    String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center=Brooklyn+Bridge,New+York,NY&zoom=13&size=600x300&maptype=roadmap" +
////"&markers=color:blue%7Clabel:S%7C40.702147,-74.015794&markers=color:green%7Clabel:G%7C40.711614,-74.012318" +
////"&markers=color:red%7Clabel:C%7C40.718217,-73.998284&path=fillcolor:0xAA000033%7Ccolor:0xFFFFFF00%7Cenc:encoded_data";
////            
//            String destinationFile = "image.jpg";
//            String str = destinationFile;
//            URL url = new URL(imageUrl);
//            InputStream is = url.openStream();
//            OutputStream os = new FileOutputStream(destinationFile);
//
//            byte[] b = new byte[2048];
//            int length;
//
//            while ((length = is.read(b)) != -1) {
//                os.write(b, 0, length);
//            }
//
//            is.close();
//            os.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.exit(1);
//        }
//
//        test.add(new JLabel(new ImageIcon((new ImageIcon("image.jpg")).getImage().getScaledInstance(630, 600,
//                java.awt.Image.SCALE_SMOOTH))));
//
//        test.setVisible(true);
//        test.pack();
//   }
//  }
//  else
//  {
//   System.out.println(res.getStatus());
//  }
//  
//  System.out.println("\n");
//  GoogleResponse res1 = new AddressConverter().convertFromLatLong("18.92038860,72.83013059999999");
//  if(res1.getStatus().equals("OK"))
//  {
//   for(Result result : res1.getResults())
//   {
//    System.out.println("address is :"  +result.getFormatted_address());
//   }
//  }
//  else
//  {
//   System.out.println(res1.getStatus());
//  }
//  
//  
//     
//
//    }
//  
//  
// 
 

}

