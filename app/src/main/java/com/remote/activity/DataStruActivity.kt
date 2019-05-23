package com.remote.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.remote.R
import kotlinx.android.synthetic.main.activity_data_stru.*
import java.util.*
import kotlin.Comparator
import kotlin.collections.HashSet

/**
 * 常见的数据结构有：Set（集）  List（列表）   Map（映射）
 *
 *
 * Collection它是所有集合类的接口，Set和List也都实现Collection接口
 *
 *
 * Collection中包含的方法有常见的：
 * boolean add(Object o)    ：向集合中加入一个对象的引用
 * void clear()   ：删除集合中所有的对象，即不再持有这些对象的引用
 * boolean isEmpty()    ：判断集合是否为空
 * boolean contains(Object o) ： 判断集合中是否持有特定对象的引用
 * Iterartor iterator()  ：返回一个Iterator对象，可以用来遍历集合中的元素(hasNext()：判断集合中元素是否遍历完毕，如果没有，就返回true   next() ：返回下一个元素   remove()：从集合中删除上一个有next()方法返回的元素。)
 * boolean remove(Object o) ：从集合中删除一个对象的引用
 * int size()       ：返回集合中元素的数目
 * Object[] toArray()    ： 返回一个数组，该数组中包括集合中的所有元素
 *
 *
 *
 *
 */
@SuppressLint("Registered")
class DataStruActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_stru)
        initSetView()
    }


    /**
     * Set是最简单的一种集合。集合中的对象不按特定的方式排序，并且没有重复对象。 Set接口主要实现了两个实现类：HashSet   TreeSet
     */
    private fun initSetView() {

        //HashSet类按照哈希算法来存取集合中的对象，存取速度比较快，HashSet是根据hashCode来决定存储位置的，是通过HashMap实现的，所以对象必须实现hashCode()方法，存储的数据无序不能重复，可以存储null,但是只能存一个
        hashSetBtn.setOnClickListener {
            val mySet = HashSet<String>()
            mySet.add("5")
            mySet.add("1")
            mySet.add("3")
            mySet.add("1")
            for (item in mySet) {
                Log.e("hashSetTest", item)
            }
        }


        //TreeSet类实现了SortedSet接口，能够对集合中的对象进行排序,TreeSet是根据二叉树实现的，也就是TreeMap, 放入数据不能重复且不能为null,可以重写compareTo()方法来确定元素大小，从而进行升序排序
        treeSetBtn.setOnClickListener {
            val mySet = TreeSet<Int>(MyComparator())
            mySet.add(1)
            mySet.add(3)
            mySet.add(2)

            for (item in mySet) {
                Log.e("treeSetTest", "$item")
            }
        }
    }


    /**
     * 排序操作
     */
    class MyComparator : Comparator<Int> {
        override fun compare(a: Int?, b: Int?): Int {
            if (a!! < b!!) return -1
            if (a == b) return 0
            if (a > b) return 1
            return 0
        }

    }
}