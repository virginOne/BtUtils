package com.zjj.bt.dht;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *ClassName:Bucket
 *@Description:
 *
 *@Author zjj
 *@Date 2019年9月9日
 *@Version 1.0
 */
public class Bucket<E extends BucketCheck> extends ArrayList<E> implements List<E>{
	private static final long serialVersionUID = 1L;
	private int k=8;
	private int size=0;
	private Object[] elementData;	
	public Bucket(){
		elementData=new Object[k];
	}
	public Bucket(int k) {
		elementData=new Object[k];
		this.k=k;
	}
	@Override
	public int size() {
		return size;
	}
	@Override
	public boolean isEmpty() {
		if(size<1)
			return true;
		return false;
	}
	@Override
	public boolean contains(Object o) {
		for(Object obj:elementData) {
			if(o.equals(obj))
				return true;
		}
		return false;
	}
	@Override
	public int indexOf(Object o) {
		for(int i=0;i<size;i++) {
			if(o.equals(elementData[i]))
				return i;
		}
		return -1;
	}
	@Override
	public int lastIndexOf(Object o) {
		int j=-1;
		for(int i=0;i<size;i++) {
			if(o.equals(elementData[i]))
				j=i;
		}
		return j;
	}
	@Override
	public Object clone() {
		Bucket<?> v = (Bucket<?>) super.clone();
		v.elementData = Arrays.copyOf(elementData, size);
		return v;
	}
	@Override
	public Object[] toArray() {
		return Arrays.copyOf(elementData, size);
	}
	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		if (a.length < size)
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
	}
	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {
		rangeCheck(index);
		return (E) elementData[index];
	}
	@Override
	public E set(int index, E element) {
		rangeCheck(index);
		E old=(E) elementData[index];
		elementData[index]=element;
		return old;
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean add(E e) {
		if(size>=k) {
			for(int i=0;i<size;i++) {
				if(!((E)elementData[i]).check()) {
					set(i, e);
					size++;
					return true;
				}
			}
		}else {
			elementData[++size]=e;
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public E remove(int index) {
		rangeCheck(index);

        E oldValue = (E) elementData[index];

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                             numMoved);
        elementData[--size] = null; // clear to let GC do its work

        return oldValue;
	}
	@Override
	public boolean remove(Object o) {
		int index=indexOf(o);
		if(index<0) {
			return false;
		}
		remove(index);
		return true;
	}
	@Override
	public void clear() {
		for (int i = 0; i < size; i++)
            elementData[i] = null;

        size = 0;
	}
	private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
	private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }
}
