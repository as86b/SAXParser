/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classroom;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/*
 * @author dale
 * http://www.saxproject.org/apidoc/org/xml/sax/helpers/DefaultHandler.html
 * https://docs.oracle.com/javase/8/docs/api/org/xml/sax/SAXException.html#SAXException--
 */
public class CourseXMLLoader {
    
    //load is a static method. You can tell because of the italics for one
    public static Course load(File xmlCourseFile) throws Exception {
        //If we did not know we would even have a course to use, we could move
        //new course down into the beginning of startElement when we would
        //know for sure that we have a class to create
        Course course = new Course();
        //when we are doing all of our code, always nice to have it in a big try
        //so we can catch that good exception that would get thrown up if 
        //something bad happens
        try {
            //notice we did not use new here and used newInstance so the
            //program keeps track of it whatever that means
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            
            //We are making a variable but we have a curly brace this is an 
            //Annonymous Inner Class
            DefaultHandler handler = new DefaultHandler() {
                Student student = null;
                boolean pawprintFlag = false;
                boolean firstNameFlag = false;
                boolean lastNameFlag = false;
                boolean gradeFlag = false;
                
                //This function is when an open tag or the start of a node is encountered
                //Not sure what all of the parameters are
                //qname is the name inside of the tags
                //Attributes is the stuff that exists within your tags
                //when parsing the XML.
                //<student "id" = 100></student>
                //id would be the attribute here
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    
                    //ignore case as in ignore upper or lower case not if you
                    //encounter the word in a tag
                    if (qName.equalsIgnoreCase("student")) {
                        //everytime the parser hits a new open student tag, will
                        //know to make a new student
                        student = new Student();
                        String idString = attributes.getValue("id");
                        if (idString != null) {
                            int id = 0;
                            try {
                                //gotta convert the string from the id attribute
                                //to a string
                                id = Integer.parseInt(idString);
                            //this kind of exception is thrown from parseInt
                            //when it fails if we are not given a string in the
                            //id attribute
                            } catch (NumberFormatException e) {
                                throw new SAXException("student id in xml could not be converted to an int");
                            }
                            student.setId(id);
                        }
                        
                    }
                    //these are all for checking if the rest of the tags or 
                    //nodes exist and if they exist in the order they should be
                    if (qName.equalsIgnoreCase("pawprint")) {
                        pawprintFlag = true;
                    }
                    if (qName.equalsIgnoreCase("firstname")) {
                        firstNameFlag = true;
                    }
                    if (qName.equalsIgnoreCase("lastname")) {
                        lastNameFlag = true;
                    }
                    if (qName.equalsIgnoreCase("grade")) {
                        gradeFlag = true;
                    }
                }
                
                //When we get to a closed tag (or end of a node) is encountered
                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (qName.equalsIgnoreCase("student")) {
                        course.addStudent(student);
                        student = null;
                    }
                    if (qName.equalsIgnoreCase("pawprint")) {
                        //These turn off our flags we set so we can set them
                        //again if we need to do this all over and check
                        //for their presence
                        pawprintFlag = false;
                    }
                    if (qName.equalsIgnoreCase("firstname")) {
                        firstNameFlag = false;
                    }
                    if (qName.equalsIgnoreCase("lastname")) {
                        lastNameFlag = false;
                    }
                    if (qName.equalsIgnoreCase("grade")) {
                        gradeFlag = false;
                    }
                }
                
                //when we have to deal with chracters from things like the pawprint
                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    //this sets a pawprintFlag when it finds pawprint
                    if (pawprintFlag) {
                        if (student != null) {
                            //THESE BUILD STRINGS FROM CHARACTERS
                            student.setPawprint(new String(ch, start, length));
                        }
                    }
                    if (firstNameFlag) {
                        if (student != null) {
                            student.setFirstName(new String(ch, start, length));
                        }
                    }
                    if (lastNameFlag) {
                        if (student != null) {
                            student.setLastName(new String(ch, start, length));
                        }
                    }
                    if (gradeFlag) {
                        if (student != null) {
                            String gradeString = new String(ch, start, length);
                            double grade = 0.0;
                            try {
                                grade = Double.parseDouble(gradeString);
                            } catch (NumberFormatException e) {
                                throw new SAXException("grade in xml could not be converted to a double");
                            }
                            
                            student.setGrade(grade);
                        }
                    }                    
                }
            };
            
            saxParser.parse(xmlCourseFile.getAbsoluteFile(), handler);
            
        } catch (Exception e) {
            throw e;
        }
        
      return course; 
    }
}
