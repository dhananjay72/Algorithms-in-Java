static void buildTree(int a[] , int s , int e , int index , int tree[]){
        if (s == e){ tree[index] = a[s] ;return; }
        int mid = (s+e)/2 ;
        buildTree(a , s , mid , 2*index , tree);
        buildTree(a , mid+1 , e , 2*index+1 , tree);
        tree[index] = Math.min(tree[2*index] , tree[2*index+1]) ;
    }


 static int lazy[]  ;

    static void upadateLazy(int tree[] , int ss , int se , int l , int r , int inc ,int index){
        if (lazy[index] != 0){
            tree[index] += lazy[index];
            lazy[2*index] = lazy[2*index+1] = lazy[index];
        }
        lazy[index] = 0 ;

        if (ss > r || l > se)return;
        if (ss >=l && se <=r){
            tree[index] += inc ;
            if (ss != se){
                lazy[2*index] += inc ;
                lazy[2*index+1] += inc ;
            }
            return;
        }

        int mid = (ss+se)/2 ;
        upadateLazy(tree ,ss , mid , l , r , inc , 2*index);
        upadateLazy(tree ,mid+1 , se , l , r , inc , 2*index+1);
        tree[index] = Math.min(tree[2*index],tree[2*index+1] ) ;
    }



    static int queryLazy(int tree[] , int ss , int se , int qs , int qe , int index){
        if (lazy[index] != 0){
            tree[index] += lazy[index];
            lazy[2*index] = lazy[2*index+1] = lazy[index];
        }
        if (ss > qe || se < qs)return Integer.MAX_VALUE ;
        if (ss >=qs && se <=qe)return tree[index];

        int mid = (ss+se)/2 ;
        int left = queryLazy(tree, ss, mid, qs, qe, 2*index) ;
        int right = queryLazy(tree, mid+1, se, qs, qe, 2*index + 1) ;
        return Math.min(left , right) ;

    }
