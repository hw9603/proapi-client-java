package com.whitepages.proapi.data.association;

import com.whitepages.proapi.data.util.TimePeriod;

public interface HistoricalAssociation {

	public TimePeriod getValidFor();

    public void setValidFor(TimePeriod validFor);

    public Boolean getHistorical();

    public void setHistorical(Boolean historical);

}
