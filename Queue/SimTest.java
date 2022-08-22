// SimTest () - M/M/1 simulation [uniform]
//
// Starting from an empty queue, simulate a single-queue, single-server system where
// inter-arrival and inter-departure times are 1, 2 or 3 time units (random uniform), and
// stop after the specified number of transactions (i.e. Clients) have completed, then
// display average time spent in the queue
//
// Challenge for the ambitious:
// ... how might this be modified to start with a non-empty queue?

import java.util.Random; // need some random numbers

class SimTest{
    private Random rg = new Random(); // random number generator
    private ClientQueue cq = new ClientQueue(); // an empty client queue
    private int simTime; // current global simulation time (arbitrary time units)
    private int nextArr, nextDep; // time of next arrival and next departure
    private int Xacts; // duration of simulation .. how many transactions (i.e. Clients) to complete
    private float totalWait; // total time all Clients spend in the queue (to compute average wait)
    
    public static void main(String [] args){
	try{ // make sure specified number of Xacts to complete is supplied
	    Integer.parseInt(args[0]);
	}catch(Exception e){// otherwise remind them and stop
	    System.err.println("Usage: java SimTest <int #transactions>");
	    return;
	}

	
	SimTest st = new SimTest(); // create a new simulation and start it up
	st.go(Integer.parseInt(args[0]));
    }

    // go() - runs a simulation
    // remaining: number of departures before ending simulation
    private void go(int remaining){
	if(remaining<1)return; // do nothing if nothing required
	Xacts = remaining;
	cq.enqueue(simTime,randT()); // start simulation at first arrival (i.e. time zero)
	nextDep = cq.peekUsage(); // compute first departure time
	nextArr = randT(); // generate next arrival time

	while(remaining > 0)// while more Xacts required, set simTime to next event
	    if(nextArr <= nextDep){ // check for arrivals first
		simTime = nextArr; // jump to next arrival time
		cq.enqueue(simTime,randT()); // create a new Client and put in queue
		nextArr = simTime + randT(); // generate random time for the next arrival
		// special case: nextDep update when queue was empty (i.e. could not know until now)
		if(cq.length()==1)nextDep = simTime + cq.peekUsage();
	    }
	    else{
		simTime = nextDep; // must be a departure next, so jump to that time
		if(cq.isEmpty()){System.err.println("ERROR: departure from empty queue! Exit!");return;}
		totalWait += simTime - cq.peekArrive(); // save time that Client spent in queue
		cq.dequeue(); // remove from queue .. all done with this Client
		remaining--; // decrement Xact count
		nextDep = (cq.isEmpty())? nextArr+1: simTime + cq.peekUsage(); // compute next departure time, if unknown set to just after nextArr
		}
	// display some performance metrics
	System.out.println("Xacts: "+Xacts+"\t Elapsed time: "+totalWait+"\t Ave wait: "+totalWait/Xacts);
    }


    // randT()
    // utility function - generates 1, 2 or 3 (random uniform, hopefully)
    private int randT(){
	return rg.nextInt(3)+1;
    }
}
