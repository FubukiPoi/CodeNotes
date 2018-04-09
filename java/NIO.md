# 什么是JAVA NIO

JAVA NIO(New IO Non Blocking IO)  java 1.4引入的API,可替代JAVA IO API。NIO支持面向缓冲区,基于通道的IO操作。NIO以更高效的方式进行文件读写操作

 IO | NIO 
------------- | ------------- 
 面向流(Stream Oriented) | 面向缓冲区(Buffer Oriented) 
 阻塞IO(Blocking IO) | 非阻塞IO(Non Blocking IO) 
 无 | 选择器(Selectors) 

# JAVA NIO核心

## 缓冲区(Buffer) 
 负责存储,缓冲区就是数组,存储不同数据类型的数据

 1. 通过allocate()获取缓冲区

    put() : 存入数据到缓冲区

    get() : 缓冲区取数据

2. 缓冲区4个核心属性 

    capacity ：容量,缓冲区最大存储数据最大容量,一旦声明不可改变

    limit : 界限,表示缓冲区中可以操作数据的大小(limite后数据不能进行读写)

    position : 位置,表示缓冲区中正在操作数据的位置

    mark : 标记,表示记录当前position的位置,可以通过reset()恢复到mark的位置

    position<=limite<=capacity

 ```
 public BufferTest{

     public void ByteBufferTest(){

         String str = "数据";

         //分配一个指定大小缓冲区
         ByteBuffer buf = ByteBuffer.allocate(1024);
         buf.position();
         buf.limit();
         buf.capacity();

         //利用put()存储数据,进入写数据模式
         buf.put(str.getBytes());

         //切换到读数据模式
         buf.flip();

         //利用get()读取缓冲区数据
         byte[] dst = new byte[buf.limit()];
         buf.get(dst);

         //rewind() 重复读数据
         buf.rewind();

         //clear()清空缓冲区,但缓冲区数据还在,"被遗忘"状态
         buf.clear();

     }

 }
 ```

 ## 直接缓冲区与非直接缓冲区

 非直接缓冲区 : 通过allocate()方法分配缓冲区,将缓冲区建立在JVM的内存中

 直接缓冲区 : 通过allocateDirect()方法分配直接缓冲区,将缓冲区建立在物理内存中,提高效率

 ## 通道(Channel) 

打开IO设备(如文件,套接字)的连接,负责连接。本身不能直接访问数据,只能与buffer交互

Channel主要实现类
>java.nio.channels.Channel api

>>FileChannel

>>SocketChannel

>>ServerSocketChannel

>>DatagramChannel



```
//利用通道完成文件复制(非直接缓冲区)
public class TestChannel{

    public void Test1(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;

        try{
            fis = new FileInputStream("1.jpg");
            fos = new FileOutputStream("2.jpg");
        
            //获取通道
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();
            //分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);
            //将通道中的数据存入缓冲区
            while(inChannel.read(buf)!=-1){
                buf.flip();//切换读取数据模式
                //将缓冲区中的数据写入通道中
                outChannel.write(buf);
                buf.clear();
        }
            
        }catch(){

        }finally{
            outChannel.close();
            inChannel.close();
            fos.close();
            fis.close();
        }
    }

    //利用通道完成文件复制(内存映射文件)
    public void Test2() throws IOException{
        FileChannel inChannel = FileChannel.open(Path.get("1.jpg",),StandarOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Path.get("3.jpg",),StandarOpenOption.WRITE,StandarOpenOption.READ);
        //内存映射文件
        MappedByteBuffer inMappedBuf = inChannel.map(MapMode.READ_ONLY,0,inChannel.size());
        MappedByteBuffer outMapedBuf = outChannel.map(MapMode.READ_WRITE,0,inChannel.size());
        //直接对缓冲区进行数据的读写操作
        byte[] dst = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dst);
        outMappedBuf.put(dst);

        inChannel.close();
        outChannel.close();
    }

    //
}
```

## 分散(Scatter)和聚集(Gather)

分散读取(Scatter Reads) : 将通道中的数据分散到多个缓冲区中

聚集写入(Gather Writes) ：将多个缓冲区中的数据聚集到通道中

```
public class TestChannel{

    //Scatter and Gather
    public void Test() throws IOException{
        //分散读取
        RandomAccessFile raf1 = new RandomAccessFile("1.txt","rw");
        //获取通道
        FileChannel channel1 = new FileChannel();
        //分配指定大小缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);
        //分散读取
        ByteBuffer[] bufs = {buf1,buf2};
        channel1.read(bufs);
        for(ByteBuffer byteBuffer : bufs){
            bufs.flip();
        }
        System.out.println(new String(bufs[0].array(),0,bufs[0].limit()));
        System.out.println(new String(bufs[1].array(),0,bufs[1].limit()));

        //聚集写入
        RandomAccessFile raf2 = new RandomAccessFile("2.txt","rw");
        //获取通道
        FileChannel channel2 = new FileChannel();
        channel2.write(bufs);
        System.out.println(new String(bufs[0].array(),0,bufs[0].limit()));
        System.out.println(new String(bufs[1].array(),0,bufs[1].limit()));
    }

}
```

## NIO的阻塞和非阻塞

