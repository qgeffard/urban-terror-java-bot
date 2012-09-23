/**
 * This class represents a data structure that links EventHandlers and their registered
 * EventTypes together. Iteration of the internals is encapsulated and taken care of by
 * a custom implemented Iterator. The EventTypes are internally stored as bit vectors
 * which provide very compact and efficient storage.
 * 
 * @author        Mathias Van Malderen
 * @version       1.0
 * @copyright     Mathias Van Malderen, 23 September, 2012
 * @package       net.goreclan.utility
 **/

package net.goreclan.utility;

import java.util.EnumSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import net.goreclan.event.EventType;

public class HandlersDS {
    
    
    /**
     * The default percentage of EventHandlers with no registered EventTypes, which
     * - if exceeded - will trigger "garbage collection".
     **/
    public static final double DEFAULT_GC_TRIGGER_PERCENTAGE = 0.15;
    
    
    /**
     * Internal Map that links EventHandlers to their registered EventTypes.
     **/
    private Map<EventHandler, Set<EventType>> eventHandlers;
    
    
    /**
     * This instance variable indicates if the eventHandlers Map should
     * automatically be made more compact when the percentage of EventHandlers
     * with no registered EventTypes exceeds a specified upper bound
     * (defined by gcTriggerPercentage).
     **/
    private boolean autoOptimize;
    
    
    /**
     * This instance variable keeps track of the amount of EventHandlers
     * in the eventHandlers Map for which there are no EventTypes registered.
     **/
    private int removedCount;
    
    
    /**
     * This instance variable holds a double value indicating the maximum
     * percentage of EventHandlers with no registered EventTypes in the
     * internal Map. If this percentage is exceeded, then the eventHandlers Map
     * will be made more compact by removing the EventHandler entries with no
     * registered EventTypes.
     **/
    private double gcTriggerPercentage;
    
    
    /**
     * Class constructor.
     * 
     * @author Mathias Van Malderen
     **/
    public HandlersDS() { this(true, DEFAULT_GC_TRIGGER_PERCENTAGE); }
    
    
    /**
     * Class constructor.
     * 
     * @author Mathias Van Malderen
     **/
    public HandlersDS(boolean autoOptimizeMemoryUsage, double maxUnusedMemoryRatio) {
        
        this.eventHandlers = new IdentityHashMap<EventHandler, Set<EventType>>();
        this.autoOptimize = autoOptimizeMemoryUsage;
        this.removedCount = 0;
        this.gcTriggerPercentage = maxUnusedMemoryRatio;
        
    }
    
    
    /**
     * Subscribe an EventHandler to events of the specified type.
     * If the specified handler is already subscribed to the specified event type,
     * then nothing happens.
     * 
     * @author Mathias Van Malderen
     **/
    public void registerEvent(EventType eventType, EventHandler handler) {
        
        // Generate an NPE to prevent null references from being added into
        // the handlers datastructure (and to prevent future NPEs when onEvent()
        // would be called for this null handler). An eventType of null would cause
        // the removedCount to be decremented unnecessarily since the EnumSet remains
        // empty. Therefore an NPE is thrown for eventType == null as well.
        if (handler == null || eventType == null) throw new NullPointerException();
        
        // Get subscribed events for the specified event handler.
        Set<EventType> subscribedEvents = eventHandlers.get(handler);
        
        if (subscribedEvents == null) {
            // The specified handler is a new handler that has not been
            // registered for other event types. Register new handler.
            eventHandlers.put(handler, EnumSet.of(eventType));
        }
        else {
            // The specified handler is registered already, but it might be
            // that it isn't registered for the specified event type yet.
            // Register the specified event type if necessary.
            if (subscribedEvents.isEmpty()) removedCount--;
            subscribedEvents.add(eventType);
        }
        
    }
    
    
    /**
     * Unsubscribe an EventHandler from receiving events of the specified event type.
     * If the specified handler is not registered for the specified event type,
     * then nothing happens.
     * 
     * @author Mathias Van Malderen
     **/
    public void unregisterEvent(EventType eventType, EventHandler handler) {
        
        Set<EventType> subscribedEvents = eventHandlers.get(handler);
        
        if (subscribedEvents != null) {
            
            subscribedEvents.remove(eventType);
            if (subscribedEvents.isEmpty()) removedCount++;
            
        }
        
    }
    
    
    /**
     * Unsubscribe an EventHandler from all the event types it is registered for.
     * If the specified handler is not registered for any event type,
     * then nothing happens.
     * 
     * @author Mathias Van Malderen
     **/
    public void unregisterEvents(EventHandler handler) {
        
        Set<EventType> subscribedEvents = eventHandlers.get(handler);
        
        if (subscribedEvents != null) {
            
            subscribedEvents.clear();
            removedCount++;
        
        }
        
    }
    
    
    /**
     * Clean up any internal Map entries (EventHandlers) that have no registered EventTypes.
     * 
     * @author Mathias Van Malderen
     **/
    public void collectGarbage() {
        
        if (removedCount > 0) {
            
            Iterator<Map.Entry<EventHandler, Set<EventType>>> it = eventHandlers.entrySet().iterator();
            
            while (it.hasNext()) {
                // If handler has no registered events, then remove it
                // from the Map of handlers.
                if (it.next().getValue().isEmpty()) it.remove();
            }
            
            removedCount = 0;
            
        }
        
    }
    
    
    /**
     * Calculate the percentage of EventHandlers in the Map with no
     * registered EventTypes.
     * 
     * @author Mathias Van Malderen
     **/
    private double calculateUnusedPercentage() {
        
        return ((double) removedCount / eventHandlers.size());
        
    }
    
    
    /**
     * Check if the condition for compacting the internal Map is satisfied.
     * 
     * @author Mathias Van Malderen
     **/
    private boolean isCollectGarbageConditionSatisfied() {
        
        return (calculateUnusedPercentage() >= gcTriggerPercentage);
        
    }
    
    
    /**
     * Returns an iterator that will iterate over all the event handlers that
     * are registered to receive events for the specified EventType.
     * 
     * @return Iterator<EventHandler>
     * @author Mathias Van Malderen
     **/
    public Iterator<EventHandler> iterator(EventType eventType) {
        
        if (autoOptimize && isCollectGarbageConditionSatisfied())
            collectGarbage();
        
        return new EventTypeIterator(eventType);
    
    }
    
    
    /**
     * Iterator implementation that iterates over all the stored EventHandlers
     * that have registered for a specified EventType.
     * 
     * @author Mathias Van Malderen
     **/
    private class EventTypeIterator implements Iterator<EventHandler> {
        
        private Iterator<Map.Entry<EventHandler, Set<EventType>>> iterator;
        private Map.Entry<EventHandler, Set<EventType>> current;
        private EventType filterType;
        
        
        /**
         * Class constructor.
         * 
         * @author Mathias Van Malderen
         **/
        public EventTypeIterator(EventType filterType) {
            
            this.iterator = eventHandlers.entrySet().iterator();
            this.filterType = filterType;
            this.current = null;
            
        }
        
        
        /**
         * Returns true if the iteration has more elements.
         * (In other words, returns true if next() would return an element
         * rather than throwing an exception.)
         * 
         * @return boolean
         * @author Mathias Van Malderen
         **/
        @Override
        public boolean hasNext() {
            
            if (current != null) return true;
            
            while (iterator.hasNext()) {
                
                current = iterator.next();
                
                if (current.getValue().contains(filterType)) return true;
            }
            
            current = null;
            
            return false;
            
        }
        
        
        /**
         * Returns the next element in the iteration.
         * 
         * @return EventHandler
         * @author Mathias Van Malderen
         **/
        @Override
        public EventHandler next() {
            
            if (!hasNext()) throw new NoSuchElementException();
            
            EventHandler handler = current.getKey();
            current = null;
            
            return handler;
            
        }
        
        
        /**
         * Unsupported operation.
         * 
         * @author Mathias Van Malderen
         **/
        @Override
        public void remove() { throw new UnsupportedOperationException(); }
        
    }
    
}
