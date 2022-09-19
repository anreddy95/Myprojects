from graph import *
from queueArray import *


           
g=Graph()
q=MyQueue()
q.enqueue(1)
q.enqueue(2)
q.enqueue(3)
q.enqueue(4)
q.enqueue(5)
def carbooking(y,c):
        if(y<5):
            if(q.size()>=1):
                q.dequeue()
                cost=y*c
                print(" package booked")
                print("cost-Rs",cost)
                print("Thankyou for booking ")
            else:
                print("sorry services are unavailable")
        elif(y>5 and y<10):
            if(q.size()>=2):
                q.dequeue()
                q.dequeue()
                cost=y*c
                print(" package booked")
                print("cost-Rs",cost)
                print("Thankyou for booking ")
            else:
                print("sorry services are unavailable")
        elif(y>10 and y<15):
            if(q.size()>=3):
                q.dequeue()
                q.dequeue()
                q.dequeue()
                cost=y*c
                print(" package booked")
                print("cost-Rs",cost)
                print("Thankyou for booking ")
            else:
                print("sorry services are unavailable")
        elif(y>15 and y<20):
            if(q.size()>=4):
                q.dequeue()
                q.dequeue()
                q.dequeue()
                q.dequeue()
                q.dequeue()
                cost=y*c
                print(" package booked")
                print("cost-Rs",cost)
                print("Thankyou for booking ")
            else:
                print("SORRY!! services are unavailable")
        elif(y>20 and y<25):
            if(q.size()>=5):
                q.dequeue()
                q.dequeue()
                q.dequeue()
                q.dequeue()
                q.dequeue()
                cost=y*c
                print(" package booked")
                print("cost-Rs",cost)
                print("Thankyou for booking ")
            else:
                print("SORRY!! services are unavailable")
        else:
            print("services are unavailable")

def findpath(graph,start,end,path=[]):
    path=path+[start]
    if start == end:
        print (path)
    if not g.haskey(start):
        for node in graph[start]:
            if node not in path:
                newpath=findpath(graph,node,end,path)
                if newpath:
                    return newpath
    else:
        print("PATH does not EXIST")

   
def function():
    print("______________________________welcome____________________________")
    print("")
    print("guidebook for coimbatore tourism")
    print("")
    print("To know the tourists spots near coimbatore Enter -1 ")
    print("To know the places connected to coimbatore-Enter 2 ")
    print("To know all the route from your city to coimbatore -Enter- 3 ")
    print("To know tour packages available-Enter 4")
    print("To exit-Enter 5")
    b=0
    while (b!="5"):
        print("")
        b=input("Enter ur choice  :")
        if b=="1":
            g1=Graph()
            g1.addEdge("coimbatore","greenvalley",12)
            g1.addEdge("coimbatore","mettupalayam",34)
            g1.addEdge("greenvalley","ooty",45)
            g1.addEdge("greenvalley","mettupalayam",22)
            g1.addEdge("mettupalayam","ooty",62)
            g1.addEdge("ooty","mettupalayam",82)
            g1.addEdge("perur","esha",2)
            g1.addEdge("esha","mettupalayam",10)
            print (g1.getVertices())
            
            
            
            
    
        if b=="2":
            for r in g1.getVertices():
                
                
                print (g1.getVertex(r))


        if b=="3":
            print("input only first 5 characters")
            g ={"coimb":["green","mettu"],
               "green":["mettu","ooty"],
               "ooty":["mettu"],
               "mettu":["ooty"],
               "perur":["esha"],
               "esha":["mettu"]}
            x=input("please enter starting place")
            y=input("please enter destination place")
            print("The possible routes are:")
            findpath(g,x,y)
        if b=="4":
            
            print("PACKAGES AVAILABLE")
            print("1.Fun Package \n visit to blackthunder,greenvalley,ootytrain\n cost/person=Rs.350")
            print("2.Temple package\n visit to perur temple,mettupalayam temple,esha\n cost/person=Rs.550")
            print("3.combined package \n cost/person=Rs.1000")

            z=int(input("please enter the package:"))
            print(z)
            if(z==1):
                print("_________To book package enter details_________")
                y=int(input("please enter the number of people :"))
                carbooking(y,350)
            elif(z==2):
                print("_________To book package enter details_________")
                y=int(input("please enter the number of people"))
                carbooking(y,550)
            elif(z==3):
                print("_________To book package enter details_________")
                y=int(input("please enter the number of people"))
                carbooking(y,1000)
            else:
                function()
                
        if b=="5":
            main()
            
   


def main():
    name={}
    text=""



    print ("******____________DPY tourism guide__________******")
    print("")
    print ("*______________________welcome________________*")
    print ("")
    print ("Are you a member??")
    print ("press 1-yes")
    print ("      2-no")
    print ("      3-exit")
    print("")
    h=0
    while (h != "3"):
        print("")
        h=input("Enter your status: ")
        if h == "1":
            k=input("login id:")
            v=input("password:")
            if k in name.keys():
                
                if name[k]== v:
                    function()
                else:
                    print ("login/password does not match!!!")
                    print ("login again!!")
            else:
                print ("loginID not found .  ___Become a member of dpy to use the app___")
                
        
        if h=="2":
            print("")
            print("___To become a member enter the following details___:")
            print("")
            nm=input("Enter your name(is same as your loginID):")
            i=0
            while i<len(nm):
                if (nm[i]!= " "):
                    text=text+nm[i]
                    i=i+1
            if  text not in name.keys():
                
                print ((text)+"  is your loginID.")
                print("")
                password=input("please Enter a password(min of 4 characters):")
                while len(password)<4:
                    if len(password)<4:
                        print("")
                        password=input("Re-enter your password with a minimun of 4 characters :")
                        
                       
                if len(password)>=4:
                    name[text]=password
                    print ("you have become a member of dpy tourism!!!\n You are now ready to use this app")
                    
                    text=""
                    print("________________________________________________________________________________")
                    print(name)
            else :
                print ("This id is unavailable")
        
        print ("THANK YOU!! for using dpy service")
                  
            
if __name__=="__main__":
    main()
    
    
