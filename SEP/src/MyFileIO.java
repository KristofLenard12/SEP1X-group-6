import java.io.*;
import java.util.ArrayList;

public class MyFileIO
{
   /**
    *
    * @param fileName file name
    * @param obj specific object that should be written
    * @throws FileNotFoundException throws this exception if no such file exists
    * @throws IOException throws this exception on error while reading file
    * Writes the given object to a file with the given file name
    */
   public void writeToFile(String fileName, Object obj) throws FileNotFoundException, IOException
   {
      ObjectOutputStream writeToFile = null;

      try
      {
         FileOutputStream fileOutStream = new FileOutputStream(fileName);
         writeToFile = new ObjectOutputStream(fileOutStream);

         writeToFile.writeObject(obj);
      }
      finally
      {
         if (writeToFile != null)
         {
            try
            {
               writeToFile.close();
            }
            catch (IOException e)
            {
               System.out.println("IO Error closing file " + fileName);
            }
         }
      }
   }
   /**
    *
    * @param fileName file name
    * @param objs Array of objects that should be written
    * @throws FileNotFoundException throws this exception if no such file exists
    * @throws IOException throws this exception on error while reading file
    * Writes the objects in the given array to a file with the given file name
    */
   public void writeToFile(String fileName, Object[] objs) throws FileNotFoundException, IOException
   {
      ObjectOutputStream writeToFile = null;

      try
      {
         FileOutputStream fileOutStream = new FileOutputStream(fileName);
         writeToFile = new ObjectOutputStream(fileOutStream);

         for (int i = 0; i < objs.length; i++)
         {
            writeToFile.writeObject(objs[i]);
         }
      }
      finally
      {
         if (writeToFile != null)
         {
            try
            {
               writeToFile.close();
            }
            catch (IOException e)
            {
               System.out.println("IO Error closing file " + fileName);
            }
         }
      }
   }
   


   /**
    *
    * @param fileName file name
    * @return Reads the first object from the file with the given file name and returns it.
    * Whoever calls the method will need to cast it from type Object to its real type
    * @throws FileNotFoundException throws this exception if no such file exists
    * @throws IOException throws this exception on error while reading file
    * @throws ClassNotFoundException throws this exception if no such class exists
    */
   public Object readObjectFromFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException
   {
      Object obj = null;
      ObjectInputStream readFromFile = null;
      try
      {
         FileInputStream fileInStream = new FileInputStream(fileName);
         readFromFile = new ObjectInputStream(fileInStream);
         try
         {
            obj = readFromFile.readObject();
         }
         catch (EOFException eof)
         {
           //Done reading
         }
      }
      finally
      {
         if (readFromFile != null)
         {
            try
            {
               readFromFile.close();
            }
            catch (IOException e)
            {
               System.out.println("IO Error closing file " + fileName);
            }
         }
      }

      return obj;
   }
 
   /**
    *
    * @param fileName file name
    * @return Reads all objects from the file with the given file name and returns it as an Object[].
    * Whoever calls the method will need to cast the Objects to their real type
    * @throws FileNotFoundException throws this exception if no such file exists
    * @throws IOException throws this exception on error while reading file
    * @throws ClassNotFoundException throws this exception if no such class exists
    */
   public Object[] readArrayFromFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException
   {
      ArrayList<Object> objs = new ArrayList<Object>();

      ObjectInputStream readFromFile = null;
      try
      {
         FileInputStream fileInStream = new FileInputStream(fileName);
         readFromFile = new ObjectInputStream(fileInStream);
         while (true)
         {
            try
            {
               objs.add(readFromFile.readObject());
            }
            catch (EOFException eof)
            {
              //Done reading
               break;
            }
         }
      }
      finally
      {
         if (readFromFile != null)
         {
            try
            {
               readFromFile.close();
            }
            catch (IOException e)
            {
               System.out.println("IO Error closing file " + fileName);
            }
         }
      }

      return objs.toArray();
   }
 }
