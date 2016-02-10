/**
 * Created by say on 06.01.16.
 */
package maths
fun sin(v:Double): Double = Math.sin(v)
fun cos(v:Double): Double = Math.cos(v)
fun tan(v:Double): Double = Math.tan(v)
fun asin(v:Double): Double = Math.asin(v)
fun acos(v:Double): Double = Math.acos(v)
fun atan(v:Double): Double = Math.atan(v)
fun toRadians(v:Double): Double = Math.toRadians(v)
fun toDegrees(v:Double): Double = Math.toDegrees(v)
fun exp(v:Double): Double = Math.exp(v)
fun log(v:Double): Double = Math.log(v)
fun log10(v:Double): Double = Math.log10(v)
fun sqrt(v:Double): Double = Math.sqrt(v)
fun cbrt(v:Double): Double = Math.cbrt(v)
fun ceil(v:Double): Double = Math.ceil(v)
fun floor(v:Double): Double = Math.floor(v)
fun rint(v:Double): Double = Math.rint(v)
fun atan2(v:Double,x: Double): Double = Math.atan2(v, x)
fun pow(v:Double,exp: Double): Double = Math.pow(v, exp)
fun pow(v:Double,exp: Int): Double = Math.pow(v, exp.toDouble())
fun round(v:Double): Long = Math.round(v)
fun abs(v:Double): Double = Math.abs(v)
fun ulp(v:Double): Double = Math.ulp(v)
fun signum(v:Double): Double = Math.signum(v)
fun sinh(v:Double): Double = Math.sinh(v)
fun cosh(v:Double): Double = Math.cosh(v)
fun tanh(v:Double): Double = Math.tanh(v)
fun expm1(v:Double): Double = Math.expm1(v)
fun log1p(v:Double): Double = Math.log1p(v)
fun copySign(v:Double,sign: Double): Double = Math.copySign(v, sign)
fun exponent(v:Double): Int = Math.getExponent(v)
fun next(v:Double,direction: Double): Double = Math.nextAfter(v, direction)
fun nextUp(v:Double): Double = Math.nextUp(v)
fun scalb(v:Double,scaleFactor: Int): Double = Math.scalb(v, scaleFactor)
fun exp(c:Complex):Complex = c.exp()
fun pow(c:Complex,d:Complex) = c.pow(d)
fun pow(c:Complex,d:Double) = c.pow(d)
fun ln(c:Complex) = c.ln()
operator fun Double.div(c:Complex): Complex = Complex(this,0.0)/c
operator fun Int.div(c:Complex): Complex = Complex(this.toDouble(),0.0)/c
operator fun Double.times(c:Complex): Complex = c*this
fun Double.toComplex() = Complex(this,0.0)
fun max(x:Double,y:Double):Double = if(x<y) y else x
fun max(x:Int,y:Int):Int = if(x<y) y else x
fun abs(x:Complex):Double = x.module()
fun real(x:Complex):Double = x.re
class Complex(var re: Double, var im: Double) {


    // Constructors
    constructor(re: Double) : this(re, 0.0)
    constructor(re: Float) : this(re.toDouble(), 0.0)
    constructor(re: Int) : this(re.toDouble(), 0.0)
    constructor(re: Long) : this(re.toDouble(), 0.0)
    constructor(re: Short) : this(re.toDouble(), 0.0)
    // Unary operators
    fun exp(x:Complex):Complex = Complex(re, -im) // conjugate
    fun module():Double = sqrt(pow(re, 2) + pow(im, 2))
    // Comparison
    operator fun compareTo(that: Complex) = this.module().compareTo(that.module())

    // Arithmetic operations
    fun pow(c:Complex):Complex = (c*this.ln()).exp()
    fun pow(c:Double):Complex = (this.ln()*c).exp()

    fun exp():Complex  {
        val r: Double = exp(this.re)
        return Complex(r*cos(this.im),r*sin(this.im))
    }

    fun arg(): Double {
        return when  {
            this.re==0.0 && this.im>0.0 -> Math.PI/2
            this.re==0.0 && this.im<0.0 -> -Math.PI/2
            this.re>0.0  -> Math.atan(this.im/this.re)
            this.re<0.0 && this.im>=0.0 -> Math.atan(this.im/this.re)+Math.PI
            this.re<0.0 && this.im<0.0 -> Math.atan(this.im/this.re)-Math.PI
            else ->  0.0
        }
    }
    fun ln():Complex  {

        return Complex(log(this.module()),arg())
    }

    operator fun plus(c: Complex):Complex = Complex(re + c.re, im + c.im)
    operator fun minus(c: Complex):Complex = Complex(re-c.re,im-c.im)
    operator fun plus(c: Double):Complex = Complex(re + c, im)
    operator fun minus(c: Double):Complex = Complex(re - c, im)
    operator fun times(c: Complex):Complex =Complex(re * c.re - im * c.im, im * c.re + re * c.im)
    operator fun times(d: Double):Complex =Complex(re * d, im * d )
    operator fun div(c: Complex):Complex {
        val d = pow(c.re, 2) + pow(c.im, 2)
        return Complex((re * c.re + im * c.im) / d, (im * c.re - re * c.im) / d)
    }
    operator fun div(c: Double):Complex  = Complex(re / c, im / c)




    // String representation
    override fun toString() = asString()


    fun asString():String = this.re.toString() + (if (this.im < 0) "-" + -this.im else "+" + this.im) + "*i"



    // Factory methods
    fun apply(re: Double) = Complex(re)
    fun ln(c:Complex):Complex = c.ln()

    // Implicit conversions
    fun fromDouble(d: Double):Complex = Complex(d)
    fun fromFloat(f: Float):Complex =  Complex(f)
    fun fromLong(l: Long):Complex =  Complex(l)
    fun fromInt(i: Int):Complex =  Complex(i)
    fun fromShort(s: Short):Complex =  Complex(s)


}


fun Array<Double>.geti(x:Double):Int
{
    var i1:Int = 0
    var i2:Int = this.size-1

    while((i2-i1)>1)
    {
        val i = (i1+i2)/2
        if(x<this[i]) i2 = i else i1 = i
    }
    return i1

}

fun Array<Double>.reaxis(xx:Array<Double>,xn:Array<Double>):Array<Double>
{
    return Array<Double>(xn.size, {i->this.getx(xn[i],xx)})
}

fun Array<Double>.reaxis(xx:Array<Double>,a:Double,b:Double,n:Int):Array<Double>
{
    val h=(b-a)/n
    return Array<Double>(n, {i->this.getx(a+i*h,xx)})
}

fun Array<Double>.reaxis(xx:Array<Double>,a:Double,b:Double,h:Double):Array<Double>
{
    val n=((b-a)/h).toInt()
    return Array<Double>(n, {i->this.getx(a+i*h,xx)})
}



fun Array<Double>.getx(x:Double,xx:Array<Double>):Double
{
    val i = xx.geti(x)
    val a = (this[i+1]-this[i])/(xx[i+1]-xx[i])
    val b = this[i+1]-a*xx[i+1]
    return if(x<xx[0]) this[0] else if(x>xx.last()) this.last() else a*x+b
}

fun Array<Array<Double>>.getxy(x:Double,y:Double,xx:Array<Double>,yy:Array<Double>):Double
{
    val i = yy.geti(y)
    val v1 = this[i].getx(x,xx)
    val v2 = this[i+1].getx(x,xx)
    val a = (v2-v1)/(yy[i+1]-yy[i])
    val b = v2-a*yy[i+1]

    return if(y<yy.first()) v1 else if(y>yy.last()) v2 else a*y+b

}

fun Array<Double>.getx(x:Double,a:Double,h:Double):Double
{
    var i = ((x-a)/h).toInt()


    if(i<0) i = 0
    if(i>=this.size-1) i = this.size-2

    val aa = (this[i+1]-this[i])/h
    val b = this[i]-aa*(i*h+a)

    return (aa*x+b)
}

