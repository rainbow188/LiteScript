# The liteScipt Interpreter
## 1.部署开发环境
### 第一步
Windows:
下载LiteSciptInstallation.exe
双击运行,填写安装目录,确定安装,等待完成
Linux:
下载LiteSciptInstallation.jar
输入java -jar LiteSciptInstallatiob.jar运行
输入安装目录,等待安装完成
### 第二步
部署环境变量，path中添加你的litescipt安装目录

## 2.LiteScipt介绍
LiteScipt是一个开源的编程语言,它的构造简单,
使一些程序开发变得简单

### 语言特色:
简洁 快速 安全 
多线程 有趣 开源

## 3.我的第一个LiteScipt程序
HelloWorld.lsp 文件:
import <string.lsp>
fun main(){
  print("HelloWorld")
}

要执行以上代码只需要输入
litescipt -lsp HelloWorld.lsp即可运行
或进入LiteScipt输入lsp HelloWorld.lsp
它会输出:
>>HelloWorld

需要注意的是,在LiteScipt中{是不能单独在一行的,
如下面的代码就是错误的
fun main()
{
   
}

## 4.LiteScipt的保留字,标识符
保留字:
int double char fun main
print import
标识符
() { }
你的变量不能用这些当名字
否则会引起错误

### LiteScipt语言的空格
LS语言中变量申明必须用空格分隔
这样你的语言才让解释器更能读懂
如:
int a = 10
double b = 20.0
int c = a+b*c
在LS语言中,代码必须分行分隔
因为在LS中每行代码后面不需要用分号进行分隔

## 5.LiteScipt基本数据类型
在LS中有下面几种基本数据类型
int double char bool
以及派生数据类型(需要调用bin类)
(a) 数组类型 使用import <array.lsp>
(b) 字符串类型 使用 import <string.lsp>

## 6.LiteScipt语言变量
申明变量一般是 数据类型 变量名 = 值
当然你也可以直接申明不赋值,这样它的默认值就是0(NULL)
如:
int a
int b = 4+a
print(b) 
这个时候程序会输出
>>4

## 7.LiteScipt函数与解释器原理
在LS中,解释器分为两次读取
第一次是扫读所有代码,检查语法并将合法的变量初始化赋值
第二次是从main函数开始读,并从栈中执行语句
每个lsp文件中必备一个main函数,可以不用标注返回类型,如
fun main(){
}
当然你也可以自定义函数,如
fun main(){
int b = 10
int a = testF(b)
}
fun testF(int v):int{
return v
}
