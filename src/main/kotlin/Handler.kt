/**
 * @author kaibo
 * @date 2019/8/15 15:04
 * @GitHub：https://github.com/yuxuelian
 * @email：kaibo1hao@gmail.com
 * @description：
 */
open class Handler(private val looper: Looper) {

    fun sendMessageDelay(message: Message, delay: Long) {
        message.target = this
        message.`when` = Math.max(System.currentTimeMillis() + delay, 0L)
        looper.messageQueue.enqueueMessage(message)
    }

    fun sendMessage(message: Message) {
        sendMessageDelay(message, 0)
    }

    fun postDelay(callback: Runnable, delay: Long) {
        val message = Message()
        message.target = this
        message.`when` = Math.max(System.currentTimeMillis() + delay, 0L)
        message.callback = callback
        looper.messageQueue.enqueueMessage(message)
    }

    fun post(callback: () -> Unit) {
        postDelay(Runnable { callback() }, 0)
    }

    open fun dispatchMessage(message: Message) {

    }
}
