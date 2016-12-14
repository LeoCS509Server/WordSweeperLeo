package admin.admincontroller;



import xml.Message;
/**
 * Interface of responsibility chain
 * @author Zetian
 *
 */
public interface IAdminClientController {
	
	/**
	 * Return TRUE if accept the response; false otherwise.
	 * 
	 * If unable to process a valid response, then must thrown a RuntimeException 
	 */
	public boolean process(Message response);
}
