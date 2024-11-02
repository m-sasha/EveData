package eve.data.utils

import java.io.InputStream
import java.io.OutputStream


/**
 * An un-synchronized implementation of an [InputStream] from a byte-array.
 * It also doesn't support mark/reset, for simplicity of implementation.
 */
class UnsyncByteArrayInputStream(
    private val buf: ByteArray
) : InputStream() {


    /**
     * The index of the next byte to read.
     */
    private var position: Int = 0


    /**
     * The size of the buffer.
     */
    private val size = buf.size


    override fun read(): Int {
        if (position >= size)
            return -1

        return buf[position++].toInt() and 0xff
    }


    override fun read(buf: ByteArray, offset: Int, length: Int): Int {
        if (position >= size)
            return -1

        val count = length.coerceAtMost(size - position)
        if (count <= 0)
            return 0

        System.arraycopy(this.buf, position, buf, offset, count)
        position += count

        return count
    }


    override fun readAllBytes(): ByteArray {
        return buf.copyOfRange(position, size).also {
            position = size
        }
    }


    override fun readNBytes(buf: ByteArray, offset: Int, length: Int): Int {
        return read(buf, offset, length).let { count ->
            if (count == -1) 0 else count
        }
    }


    override fun transferTo(out: OutputStream): Long {
        val length = size - position
        out.write(buf, position, length)
        position = size
        return length.toLong()
    }


    override fun skip(n: Long): Long {
        val actualSkip = n.coerceIn(0 .. (size - position).toLong())
        position += actualSkip.toInt()
        return actualSkip
    }


    override fun available(): Int {
        return size - position
    }


    override fun markSupported() = false


}
