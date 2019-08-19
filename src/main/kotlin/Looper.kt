/**
 * @author kaibo
 * @date 2019/8/15 15:13
 * @GitHub：https://github.com/yuxuelian
 * @email：kaibo1hao@gmail.com
 * @description：
 */

class Looper private constructor() {
    companion object {
        private val THREAD_LOCAL = ThreadLocal<Looper>()

        fun prepare() {
            if (THREAD_LOCAL.get() != null) {
                throw RuntimeException("Only one Looper may be created per thread")
            } else {
                THREAD_LOCAL.set(Looper())
            }
        }

        fun myLooper(): Looper {
            return THREAD_LOCAL.get() ?: throw RuntimeException("not call prepare")
        }

        fun loop() {
            val looper = myLooper()
            while (true) {
                val message: Message? = looper.messageQueue.next()
                if (message == null) {
                    continue
                } else {
                    message.callback?.run() ?: message.target?.dispatchMessage(message)
                }
            }
        }
    }

    val messageQueue: MessageQueue = MessageQueue()

}
