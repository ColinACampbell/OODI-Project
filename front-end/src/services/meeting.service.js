export default{
    async getMeetingAlerts(token){
        let res = await fetch(`${process.env.VUE_APP_API_ENDPOINT}/api/meeting-alert/`,
        {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token,
            },
            
        })
        if(res.status === 200){
            return await res.json()
        }
        return "Failed to fetch"
       
    },

    async uploadAlertChanges(token, id, info){
    
        let res = await fetch(`${process.env.VUE_APP_API_ENDPOINT}/api/meeting-alert/${id}`,
        {
            method: "PUT",
            body: JSON.stringify(info),
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token,
            },
            
        })
        if(res.status === 200){
            return await res.json()
        } else if(res.status == 409){
            return "Title duplication"
        }
        return "Failed to update"
    },

}