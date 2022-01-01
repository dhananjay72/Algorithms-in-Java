 static void buildTree(int a[] , int s , int e , int index , int tree[]){
        if (s == e){ tree[index] = a[s] ;return; }
        int mid = (s+e)/2 ;
        buildTree(a , s , mid , 2*index , tree);
        buildTree(a , mid+1 , e , 2*index+1 , tree);
        tree[index] = Math.min(tree[2*index] , tree[2*index+1]) ;
    }

    static int query(int tree[] , int ss , int se , int qs , int qe , int index){
        //complete overlap :
        if (ss >= qs && se <=qe)return tree[index];
        // NO overlap :
        if (qe < ss ||qs > se)return Integer.MIN_VALUE ;

        //partial overlap :
        int mid = (ss+se)/2 ;
        int left = query(tree , ss , mid , qs , qe , 2*index) ;
        int right = query(tree , mid+1 , se , qs , qe , 2*index+1) ;
        return  Math.max(left , right) ;
    }

    static void update(int tree[] , int ss , int se , int i , int increament , int index){
        if (i > se || i < ss)return;
        //leaf node :
        if (ss == se){
            tree[index] += increament ;
            return;
        }
        int mid = (ss+se)/2 ;
        update(tree , ss , mid , i , increament , 2*index);
        update(tree , mid+1 , se , i , increament , 2*index+1);
        tree[index] = Math.min(tree[2*index],tree[2*index+1]) ;
    }

    static void updateRange(int tree[] , int ss , int se , int l , int r , int inc , int index){
        if (l>se || r<ss )return;
        //leaf Nodes :
        if (ss == se){
            tree[index] += inc ;
            return;
        }
        int mid = (ss+se)/2 ;
        updateRange(tree , ss , mid , l , r , inc , 2*index);
        updateRange(tree , mid+1 , se , l , r , inc , 2*index+1);

        tree[index] = Math.min(tree[2*index], tree[2*index+1]) ;
    }
