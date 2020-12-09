import { Employee } from '.';

export interface PaginationResponse {
    content: Array<Employee>;
    pageable: {
        sort: {
            unsorted: boolean;
            sorted: boolean;
            empty: boolean;
        };
        pageNumber: number;
        pageSize: number;
        offset: number;
        paged: boolean;
        unpaged: boolean;
    };
    totalPages: number;
    totalElements: number;
    last: boolean;
    first: boolean;
    sort: {
        unsorted: boolean;
        sorted: boolean;
        empty: boolean;
    };
    number: number;
    numberOfElements: number;
    size: number;
    empty: boolean;
}