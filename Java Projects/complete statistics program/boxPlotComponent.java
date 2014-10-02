//holds the necessary methods to draw a box plot


public class boxPlotComponent 
{
    public boxPlotComponent()
    { }//constructor        


    //gets the median of a sorted double array
    public double getMedian(double values[])
    {

            if(values.length==1)
                return values[0];
            else if(values.length%2==0)//if number of datas are even number
            {
                double first = values[(values.length/2)-1];
                double second = values[(values.length/2)];
                
                return (first + second)/2;
            } 
            else if(values.length%2==1)
            {
                return values[(values.length-1)/2];
            }    
            else
              return 0;
            
    } 

    //returns the median of the lower half
    //the array must be a sorted array t get
    //the correct result.
    public double getLowerFourth(double[] list)
    {
      double[] temp;
      
      if(list.length%2==0)
          temp = new double[list.length/2];
      else           
          temp = new double[(list.length+1)/2];
      
      for(int i=0; i<temp.length; i++)
         temp[i] = list[i];
      
      return this.getMedian(temp);
       
    } 
    
    //returns the median of the upper half
    //the array list must be sorted
    public double getUpperFourth(double[] list)
    {
        int j;
        double temp[];
        if(list.length%2==0)
        {    
          j = list.length/2;
          temp = new double[j];
        }  
        else
        {    
          j = (list.length-1)/2;
          temp = new double[j+1];
        }
        for(int i=0; i<temp.length; i++)
        {
            temp[i] = list[j];
            j++;
        }
        
        return this.getMedian(temp);               
    }
    
    //gets a double type array and returns its mean
    public double getMean(double values[])
    {
         double sum = 0.;
            
         for(int i = 0; i < values.length; i++)
               sum += values[i];
            
          return (sum/values.length); 
        
     }
    
     //returns the standard deviation of an array with mean
      public double StandardDeviation(double values[], double mean)
      {
            double result = 0., dif;
            
            for(int i = 0; i<values.length; i++)
            {
                dif = values[i]- mean;//subtraction xbar from xi
                result += java.lang.Math.pow(dif, 2.0);//power square and adds
            }
            result = result/(values.length-1);
            return java.lang.Math.sqrt(result);
                
        }

}