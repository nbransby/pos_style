import SwiftUI
import Shared

struct ContentView: View {
    private let viewModel = ProductListViewModel()
    @State private var selectedProduct: Product?
    @State private var selectedSortBy: ProductListViewModel.SortBy
    
    init() {
        selectedSortBy = viewModel.sortBy.value
    }

    var body: some View {
        NavigationView {
            VStack {
                Picker("Sort by", selection: $selectedSortBy) {
                    ForEach(ProductListViewModel.SortBy.allCases) { order in
                        Text(order.name).tag(order)
                    }
                }
                .collect(flow: viewModel.sortBy, into: $selectedSortBy)
                .onChange(of: selectedSortBy) { _, it in viewModel.sortBy.value = it }
                .pickerStyle(.segmented)
                .padding()
                Observing(viewModel.products) { products in
                    List(products, id: \.id) { product in
                        Button(action: {
                            selectedProduct = product
                        }) {
                            VStack(alignment: .leading) {
                                Text(product.name).font(.headline)
                                Text(String(format: "$%.2f", product.price)).font(.subheadline)
                            }
                        }
                      }
                }
                .navigationTitle("Products")
                .alert(item: $selectedProduct) { product in
                    Alert(title: Text(product.name),
                          message: Text(String(format: "$%.2f", product.price)),
                          dismissButton: .default(Text("OK")))
                }
            }
        }
    }
}

extension Product: Identifiable {}
extension ProductListViewModel.SortBy: Identifiable {
    public var id: String { self.name }
}
