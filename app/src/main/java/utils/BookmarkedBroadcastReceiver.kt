package utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

// An interface cannot inherit from a class or abstract class. But class or abstract class can inherit from interface.
// abstract class can inherit another class or abstract class and a class can inherit from another class or abstract class.
// But only one class can be inherited.

// So, I think it should be a normal class with companion object!? don't know. NO
// TODO:: check these notes before start implementing hte broadcast receiver.
// make a class that inherits from BroadcastReceiver without companion object.
// when you need to use it make an instance from it.
class BookmarkedBroadcastReceiver: BroadcastReceiver()
{
    override fun onReceive(context: Context?, intent: Intent?)
    {

    }
}