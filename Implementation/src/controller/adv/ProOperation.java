package controller.adv;

import controller.mainOperation;
import controller.crud.CRUDOperation;
import model.Professional;


public class ProOperation extends mainOperation{

	/**
	 *
	 * Delete a list of services by a pro's unique id.
	 * If the professional's account is deleted, the services can be too.
	 *
	 * @param proId
	 */
	public void deleteServicesByProId(String proId) {
		if(listService.size() != 0) {
			for(int i = 0; i<listService.size(); i++) {
				if(listService.get(i).getProId().equals(proId)) {
					listService.remove(i);
				}
			}
		}		
	}
}
