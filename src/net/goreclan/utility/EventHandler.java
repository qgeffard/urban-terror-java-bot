/**
 * @author        Mathias Van Malderen
 * @version       1.0
 * @copyright     Mathias Van Malderen, 08 September, 2012
 * @package       net.goreclan.utility
 **/

package net.goreclan.utility;

import net.goreclan.event.Event;

public interface EventHandler {
    
    /**
     * Callback method that is called when a registered event occurs.
     * 
     * @author Mathias Van Malderen
     */
    public void onEvent(Event evt);

}
