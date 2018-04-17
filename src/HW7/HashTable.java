//Ishaan Bharal(ixb170930)
package HW7;

public class HashTable
{
    public static void main(String[] args)
    {
	HashTable table = new HashTable();
	table.insert(992);
	table.insert(562);
	table.insert(864);
	table.insert(328);
	table.insert(888);
	for(int i = 0; i < table.arr.length; i++)
	{
	    System.out.println(i + " : " + table.search(i) + " ");
	}
	System.out.println("");
	table.insert(123);
	table.insert(826);
	table.insert(124);
	table.insert(228);
	table.insert(323);
	table.insert(653);
	table.insert(553);
	table.insert(483);
	table.insert(963);
	table.insert(155);
	table.insert(537);
	table.insert(795);
	table.insert(128);
	table.insert(987);
	for(int i = 0; i < table.arr.length; i++)
	{
	    System.out.println(i + " : " + table.search(i) + " ");
	}
	table.insert(768);
	table.insert(474);
	table.insert(215);
    }
    
    public static double load = 0.5;
    
    private Integer[] arr;
    private int size;
    
    public HashTable()
    {
	arr = new Integer[9];
	size = 0;
    }
    
    private int hashCode(int num)
    {
	int code = num % arr.length;
	int i = 1;
	while(arr[code] != null)
	{
	    code = ((int)Math.pow(code, i) + 1) % arr.length;
	    i++;
	}
	return code;
    }
    
    public int search(int key)
    {
	return arr[key] != null ? arr[key] : -1;
    }
    
    public boolean insert(int num)
    {
	if(num < 100 || num > 999)
	    return false;
	
	if(size / arr.length >= load)
	    rehash();
	
	int code = hashCode(num);
	arr[code] = num;
	
	size++;
	return true;
    }
    
    public void delete(int key)
    {
	arr[key] = 0;
    }
    
    private void rehash()
    {
	Integer[] temp = arr;
	arr = new Integer[arr.length*2];
	for(int i = 0; i < temp.length && temp[i] != null; i++)
	{
	    this.insert(temp[i]);
	}
    }
    
    public int size()
    {
	return size;
    }
}
