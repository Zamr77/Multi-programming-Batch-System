import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class test {
	public   static MutexSemaphore printSemaphore=new MutexSemaphore();
	public   static MutexSemaphore readfileSemaphore=new MutexSemaphore();
	public  static MutexSemaphore writefileSemaphore=new MutexSemaphore();
	public  static MutexSemaphore takeinputSemaphore=new MutexSemaphore();
	
	
	
	
	
public static void main(String[]args) {	
	ArrayList<ArrayList<Object>> a=new ArrayList<ArrayList<Object>>();
	ArrayList<Object> z=new ArrayList<Object>();
//	ArrayList<Object>w=new ArrayList<Object>();
//	ArrayList<Object>qArrayList=new ArrayList<Object>();
//	z.add(new Process(1));
//    z.add(7);
//	w.add(new Process(2));
//
//    w.add(1);
//	qArrayList.add(new Process(3));
//
//    qArrayList.add(2);
//    
//    a.add(z);
//    a.add(w);
//    a.add(qArrayList);
//	

   

    System.out.println(a);

	
	for(int i=0;i<a.size();i++)
    {
        int j = i;

        //i is not the first element
        while(j>0)
        {
            //not in order
            if((int)a.get(j-1).get(1) >(int) a.get(j).get(1))
            {
                //swapping
                Object temp = a.get(j-1);
                a.set(j-1, a.get(j));
                a.set(j, (ArrayList<Object>) temp);
            }
            //in order
            else
            {
                break;
            }
            j--;
        }
}
	
//	for(int i=0;i<a.size();i++)
//    {
//        int j = i;
//
//        //i is not the first element
//        while(j>0)
//        {
//            //not in order
//            if(a.get(j-1) > a.get(j))
//            {
//                //swapping
//                int temp = a.get(j-1);
//                a.set(j-1, a.get(j));
//                a.set(j, temp);
//            }
//            //in order
//            else
//            {
//                break;
//            }
//            j--;
//        }
//}
	System.out.println(a);
	
	
	
}	
	
	
}
