from time import *

def linear(n):
    """ linear O(n)"""
    cont=0
    for i in range (n):
        cont=cont+1
    print ("COUNTER",cont,end=" ")

def quadratic(n):
    """ quadratic O(n**2)"""
    cont=0
    for i in range (n):
        for j in range (n):
            cont=cont+1
    print ("COUNTER",cont,end=" ")

print("LINEAR TIMES (MILLISEC.)")
t1=0
t2=0
n=1000000
while t2-t1<5:  #5 secons
    t1=time()  
    linear(n)
    t2=time()
    print("n=",n,"***","time",int(1000*(t2-t1)))
    n=n*2

print("QUADRATIC TIMES (MILLISEC.)")
t1=0
t2=0
n=100
while t2-t1<5:  #5 seconds
    t1=time()
    quadratic(n)
    t2=time()
    print("n=",n,"***","time",int(1000*(t2-t1)))
    n=n*2