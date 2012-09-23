/**
 * This class is responsible for processing/dispatching the produced Events.
 *  
 * @author        Mathias Van Malderen
 * @version       1.0
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
    
    private BlockingQueue<Event> eventQueue;
    private HandlersDS handlersDS;
    
    
    /**
     * Class constructor
     * 
     * @author Mathias Van Malderen
     **/
    private EventProcessor(BlockingQueue<Event> eventQueue) {
        
        if (eventQueue == null) throw new NullPointerException();
        
        this.eventQueue = eventQueue;
        this.handlersDS = new HandlersDS();
        
    }
    
    
    /**
     * Event Processing
     * 
     * @author Mathias Van Malderen
     **/
    @Override
    public void run() {
        
        try {
            
            while (true) {
                
                Event event = eventQueue.take(); // should block if empty queue
                
                // Obtain an Iterator that will iterate over all the EventHandlers
                // that are registered for the eventType of the event that is being
                // processed.
                Iterator<EventHandler> it = handlersDS.iterator(event.getName());
                
                while (it.hasNext()) {
                    
                    EventHandler handler = it.next();
                    
                    // Wrapped in try-catch to avoid the EventProcessor being terminated
                    // because of an uncaught exception that could potentially occur in
                    // an improperly coded EventHandler ("plugin").
                    try {
                        handler.onEvent(event);
                    } catch (Exception e) {
                        // Log uncaught exception
                        Log.debug("Uncaught exception: " + e.toString());
                    }
                    
                }
                
            }
            
        } catch (InterruptedException e) {
            // Should never occur!
            Log.debug(e.toString());
        }
        
    }
    
    
    /**
     * Subscribe an EventHandler to events of the specified type.
     * If the specified handler is already subscribed to the specified event type,
     * then nothing happens.
     * 
     * @author Mathias Van Malderen
     **/
    public void registerEvent(EventType eventType, EventHandler handler) {
        handlersDS.registerEvent(eventType, handler);
    }
    
    
    /**
     * Unsubscribe an EventHandler from receiving events of the specified event type.
     * If the specified handler is not registered for the specified event type,
     * then nothing happens.
     * 
     * @author Mathias Van Malderen
     **/
    public void unregisterEvent(EventType eventType, EventHandler handler) {
        handlersDS.unregisterEvent(eventType, handler);
    }
    
    
    /**
     * Unsubscribe an EventHandler from all the event types it is registered for.
     * If the specified handler is not registered for any event type,
     * then nothing happens.
     * 
     * @author Mathias Van Malderen
     **/
    public void unregisterEvents(EventHandler handler) {
        handlersDS.unregisterEvents(handler);
    }
    
}
