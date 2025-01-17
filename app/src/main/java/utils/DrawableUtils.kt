package utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import java.io.ByteArrayOutputStream

abstract class DrawableUtils {

    companion object {
        fun getGradientDrawable(sizeX: Int, sizeY: Int, colors: IntArray): Drawable {
            with(GradientDrawable()) {
                setSize(sizeX, sizeY)
                orientation = GradientDrawable.Orientation.TL_BR
                this.colors = colors
                return this
            }
        }

        fun Drawable.toBytes(): ByteArray {
            val bitmap = Bitmap
                .createBitmap(
                    this.intrinsicWidth,
                    this.intrinsicHeight,
                    Bitmap.Config.ARGB_8888
                )
            val canvas = Canvas(bitmap)
            this.setBounds(0, 0, this.intrinsicWidth, this.intrinsicHeight)
            this.draw(canvas)
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            return stream.toByteArray()
        }

        fun createDrawableFromByteArray(byteArray: ByteArray, drawableName: String): Drawable {
            return Drawable.createFromStream(byteArray.inputStream(), drawableName)!!
        }
    }
}