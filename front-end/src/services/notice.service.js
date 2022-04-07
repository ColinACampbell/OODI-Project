export default {
    async getNotices(token){
        let res = await fetch(`${process.env.VUE_APP_API_ENDPOINT}/api/notice`,
        {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token,
            },
            
        })
        // if(res.status === 200){
        //     return await res.json()
        // }
        console.log(await res.json())
        return "Failed to fetch"
           
    }, 
    async uploadNoticeChanges(token, id, info){
    
        let res = await fetch(`${process.env.VUE_APP_API_ENDPOINT}/api/notice/${id}`,
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
        }
        return "Failed to update"
    }
}