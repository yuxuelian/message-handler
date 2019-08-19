import kotlin.concurrent.thread

/**
 * @author kaibo
 * @date 2019/8/15 15:01
 * @GitHub：https://github.com/yuxuelian
 * @email：kaibo1hao@gmail.com
 * @description：
 */


fun main() {
    Looper.prepare()

    val handler = object : Handler(Looper.myLooper()) {
        override fun dispatchMessage(message: Message) {
            println("${Thread.currentThread().name}-${message.obj.toString()}")
        }
    }

    thread {
        var a = 0
        while (true) {
            println("${Thread.currentThread().name}-发送消息线程")
            // 发送消息方式
            val message = Message()
            message.obj = a++
            handler.sendMessage(message)

            // 切线程方式
            handler.post {
                println("${Thread.currentThread().name}-handler.post")
            }
            Thread.sleep(1000)
        }
    }

    Looper.loop()
}
