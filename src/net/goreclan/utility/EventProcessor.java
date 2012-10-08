/**
 * This class is responsible for processing/dispatching the produced Events.
 *  
 * @author        Mathias Van Malderen
 * @version       1.1
 * @copyright     Mathias Van Malderen, 23 September, 2012
 * @package       net.goreclan.utility
 **/

package net.goreclan.utility;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;

import net.goreclan.event.Event;
import net.goreclan.event.EventType;
import net.goreclan.logger.Log;

public class EventProcessor implements Runnable {
    
	private final Log log;
    private BlockingQueue<Event> eventQueue;
    private HandlersDS handlersDS;
    
    
    /**
     * Object constructor.
     * 
     * @author Mathias Van Malderen
     * @param  eventQueue A reference to the events queue
     * @param  log A reference to the main bot logger object
     * @return EventProcessor
     **/
    private EventProcessor(BlockingQueue<Event> eventQueue, Log log) {
        
        if (eventQueue == null) 
        	throw new NullPointerException();
        
        this.eventQueue = eventQueue;
        this.handlersDS = new HandlersDS();
        this.log = log;
        
    }
    
    
    /**
     * Runnable implementation of the event processor.
     * 
     * @author Mathias Van Malderen
     **/
    @Override
    public void run() {
        
        try {
            
            while (true) {
                
                Event event = this.eventQueue.take(); // should block if empty queue
                
                // Obtain an Iterator that will iterate over all the EventHandlers
                // that are registered for the eventType of the event that is being processed.
                Iterator<EventHandler> it = this.handlersDS.iterator(event.getType());
                
                while (it.hasNext()) {
                    
                    EventHandler handler = it.next();
                    
                    // Wrapped in try-catch to avoid the EventProcessor being terminated
                    // because of an uncaught exception that could potentially occur in
                    // an improperly coded EventHandler ("plugin").
                    try { handler.onEvent(event); } 
                    catch (Exception e) { this.log.error("Uncaught exception: " + e.toString()); }
                    
                }
                
            }
            
        } catch (InterruptedException e) {
            // Should never occur!
            this.log.fatal(e.toString());
            System.exit(1);
        }
        
    }
    
    
    /**
     * Subscribe an EventHandler to events of the specified type.
     * If the specified handler is already subscribed to the specified event type,
     * then nothing happens.
     * 
     * @author Mathias Van Malderen
     * @param  eventType The type of the event to be registered
     * @param  handler The handler of such event
     **/
    public void registerEvent(EventType eventType, EventHandler handler) {
    	this.handlersDS.registerEvent(eventType, handler);
    }
    
    
    /**
     * Unsubscribe an EventHandler from receiving events of the specified event type.
     * If the specified handler is not registered for the specified event type,
     * then nothing happens.
     * 
     * @author Mathias Van Malderen
     * @param  eventType The type of the event to be unregistered
     * @param  handler The handler of such event
     **/
    public void unregisterEvent(EventType eventType, EventHandler handler) {
        this.handlersDS.unregisterEvent(eventType, handler);
    }
    
    
    /**
     * Unsubscribe an EventHandler from all the event types it is registered for.
     * If the specified handler is not registered for any event type,
     * then nothing happens.
     * 
     * @author Mathias Van Malderen
     * @param  handler The event handler
     **/
    public void unregisterEvents(EventHandler handler) {
        handlersDS.unregisterEvents(handler);
    }
    
}
