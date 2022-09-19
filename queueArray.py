class MyQueue():
    
    def __init__(self):
        # this is the queue container called 'queue'
        self.queue = []
        # front and back indexes
        self.f = 0
        self.r = -1
        # define the queue size 'max_queue_size' and initialize it
        self.max_queue_size = 10
        for i in range(0,self.max_queue_size):
            self.queue.append(None)

    # define the enqueue operation which inserts the value into the queue, must throw a queue full exception
    def enqueue(self, value):
        #print self.r, ":", self.f
        if (self.size() == self.max_queue_size):
            print("Overflow")
        else:
            if (self.r<self.max_queue_size-1):
                self.r = self.r+1 
                self.queue[self.r] = value                              
            else:
                self.r = (self.r+1)%self.max_queue_size
                self.queue[self.r] = value
                
                
            
    # returns first elt of the queue if not empty, else throws queue empty
    # exception
    
    def dequeue(self):
        #print self.f, ":", self.r
        if (self.f==self.r):
            return "Queue Empty Exception"
        else:
            obj = self.queue[self.f]                
            if (self.f<self.max_queue_size):
                self.f = (self.f+1)%self.max_queue_size
            else:
                self.f = (self.f+1)%self.max_queue_size
            return obj
    
    # returns front element without removing it if the queue is not empty, else throws exception   
    def front(self):
        if (self.f!=self.r):
            print ("returning front element")
            return self.queue[self.f]
        return "Queue Empty Exception"
     
    # returns the number of elements currently in queue
    def isEmpty(self):
        if (self.f != self.r):
            print ("Queue not empty")
            return False
        else:
            return True

    
    # returns True if queue is empty
    def size(self):
        return (self.max_queue_size - self.f + self.r)% self.max_queue_size
        #return len(self.queue)
