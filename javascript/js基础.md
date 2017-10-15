## 1.
```
var a,b;
(function(){
    alert(a);
    alert(b);
    var a=b=3;   //var a=3；b=3；a局部变量，b全局变量
    alert(a);
    alert(b);
})();
alert(a);
alert(b);
```
输出：undefined，undefined，3,3，undefined，3
***

## 2.
>当在函数中声明一个变量的时候，如果该变量前面没有带上关键字 var，这个变量就会成为
全局变量，这当然是一种容易造成命名冲突的做法。
另外一种情况是用 var 关键字在函数中声明变量，这时候的变量即是局部变量，只有在该函
数内部才能访问到这个变量，在函数外面是访问不到的。
```
var fun=function(){
    var a=1;
    alert(a);
}
fun();        // 1
alert(a);    // Uncaught ReferenceError: c is not defined
```
***